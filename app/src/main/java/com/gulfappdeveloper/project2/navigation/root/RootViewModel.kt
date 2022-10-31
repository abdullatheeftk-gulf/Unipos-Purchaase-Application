package com.gulfappdeveloper.project2.navigation.root

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulfappdeveloper.project2.data.remote.HttpRoutes
import com.gulfappdeveloper.project2.domain.models.remote.get.ClientDetails
import com.gulfappdeveloper.project2.domain.models.remote.get.GetDataFromRemote
import com.gulfappdeveloper.project2.domain.models.remote.get.ProductDetails
import com.gulfappdeveloper.project2.domain.models.util.PayMode
import com.gulfappdeveloper.project2.presentation.splash_screen.util.SplashScreenEvent
import com.gulfappdeveloper.project2.usecases.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
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

    var billNo = mutableStateOf("")
        private set

    var selectedDate = mutableStateOf(Date())
        private set

    var selectedClient:MutableState<ClientDetails?> = mutableStateOf(null)
        private set

    var poNo = mutableStateOf("")
        private set

    var payMode = mutableStateOf(PayMode.Cash)
        private set

     val productDetailsList = mutableStateListOf<ProductDetails>()

    val selectedProduct:MutableState<ProductDetails?> = mutableStateOf(null)

    val clientDetailsList = mutableStateListOf<ClientDetails>()


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
        try {
            productDetailsList.removeAll{
                true
            }
        }catch (e:Exception){
            Log.e(TAG, "getProductDetails: ${e.message}", )
        }
        viewModelScope.launch {
            useCase.getProductDetailsUseCase(url = baseUrl.value + HttpRoutes.GET_PRODUCT_DETAILS)
                .collectLatest { result ->
                    Log.w(TAG, "getProductDetails: $result")
                    if (result is GetDataFromRemote.Success) {
                        productDetailsList.addAll(result.data)

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

        try {
            clientDetailsList.removeAll {
                true
            }
        }catch (e:Exception){
            Log.e(TAG, "getClientDetails: ${e.message}", )
        }
        viewModelScope.launch {
            useCase.getClientDetailsUseCase(url = baseUrl.value + HttpRoutes.GET_CLIENT_DETAILS)
                .collectLatest { result ->
                    Log.i(TAG, "getClientDetails: $result")
                    if (result is GetDataFromRemote.Success) {
                        clientDetailsList.addAll(result.data)
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

    fun setDate(date: Date){
        selectedDate.value = date
    }

    fun setBillNo(value:String){
        billNo.value = value
    }

    fun setClientDetails(value: ClientDetails){
        selectedClient.value = value
    }

    fun setPoNo(value:String){
        poNo.value = value
    }

    fun setPayMode(value:PayMode){
        payMode.value = value
    }

    fun setSelectedProduct(productDetails: ProductDetails){
        selectedProduct.value = productDetails
    }


    private fun sendSplashScreenEvent(splashScreenEvent: SplashScreenEvent) {
        viewModelScope.launch {
            _splashScreenEvent.send(splashScreenEvent)
        }
    }

}