package com.gulfappdeveloper.project2.navigation.root

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulfappdeveloper.project2.data.remote.HttpRoutes
import com.gulfappdeveloper.project2.domain.models.remote.get.ClientDetails
import com.gulfappdeveloper.project2.domain.models.remote.get.GetDataFromRemote
import com.gulfappdeveloper.project2.domain.models.remote.get.ProductDetails
import com.gulfappdeveloper.project2.presentation.splash_screen.util.SplashScreenEvent
import com.gulfappdeveloper.project2.usecases.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "RootViewModel"

@HiltViewModel
class RootViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private val _splashScreenEvent = Channel<SplashScreenEvent>()
    val splashScreenEvent = _splashScreenEvent.receiveAsFlow()

    var operationCount = mutableStateOf(0)
        private set

    var baseUrl = mutableStateOf(HttpRoutes.BASE_URL)
        private set

    var message = mutableStateOf("")
        private set

    private val _productDetails: MutableStateFlow<List<ProductDetails>> =
        MutableStateFlow(emptyList())
    val productDetails: StateFlow<List<ProductDetails>> = _productDetails

    private val _clientDetails: MutableStateFlow<List<ClientDetails>> =
        MutableStateFlow(emptyList())
    val clientDetails: StateFlow<List<ClientDetails>> = _clientDetails


    init {
        sendSplashScreenEvent(SplashScreenEvent.ShowProgressBar)
        saveOperationCount()
    }

    private fun saveOperationCount() {
        viewModelScope.launch {
            useCase.updateOperationCountUseCase()
            readOperationCount()
        }
    }

    private fun readOperationCount() {
        viewModelScope.launch {
            useCase.readOperationCountUseCase().collectLatest {
                Log.d(TAG, "readOperationCount: $it")
                operationCount.value = it
                readBaseUrl()
            }
        }
    }

    private fun readBaseUrl() {
        viewModelScope.launch {
            useCase.readBaseUrlUseCase().collectLatest {
                Log.i(TAG, "readBaseUrl: $it")
                baseUrl.value = it
                getWelcomeMessage()
            }
        }
    }

    private fun getWelcomeMessage() {
        viewModelScope.launch {
            useCase.getWelcomeMessageUseCase(url = baseUrl.value).collectLatest { result ->
                if (result is GetDataFromRemote.Success) {
                    message.value = result.data.message
                    sendSplashScreenEvent(SplashScreenEvent.CloseProgressBar)
                    getProductDetails()
                    getClientDetails()
                }
                if (result is GetDataFromRemote.Failed) {
                    sendSplashScreenEvent(SplashScreenEvent.CloseProgressBar)
                    sendSplashScreenEvent(SplashScreenEvent.ShowToast(message = "Server with ${baseUrl.value} down, Change server"))
                    sendSplashScreenEvent(SplashScreenEvent.ShowSetBaseUrlButton)
                    Log.e(TAG, "getWelcomeMessage: ${result.error.code} ${result.error.message}")
                }
            }
        }
    }

    private fun getProductDetails() {
        viewModelScope.launch {
            useCase.getProductDetailsUseCase(url = baseUrl.value + HttpRoutes.GET_PRODUCT_DETAILS)
                .collectLatest { result ->
                    Log.w(TAG, "getProductDetails: $result")
                    if (result is GetDataFromRemote.Success) {
                        _productDetails.value = result.data
                    }
                    if (result is GetDataFromRemote.Failed) {
                        Log.e(
                            TAG,
                            "getProductDetails: ${result.error.code}, ${result.error.message} "
                        )
                    }
                }
        }
    }

    private fun getClientDetails() {
        viewModelScope.launch {
            useCase.getClientDetailsUseCase(url = baseUrl.value + HttpRoutes.GET_CLIENT_DETAILS)
                .collectLatest { result ->
                    Log.i(TAG, "getClientDetails: $result")
                    if (result is GetDataFromRemote.Success) {
                        _clientDetails.value = result.data
                    }
                    if (result is GetDataFromRemote.Failed) {
                        Log.e(
                            TAG,
                            "getClientDetails: ${result.error.code}, ${result.error.message}"
                        )
                    }
                }
        }
    }


    private fun sendSplashScreenEvent(splashScreenEvent: SplashScreenEvent) {
        viewModelScope.launch {
            _splashScreenEvent.send(splashScreenEvent)
        }
    }

}