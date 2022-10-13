package com.gulfappdeveloper.project2.navigation.root

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulfappdeveloper.project2.data.remote.HttpRoutes
import com.gulfappdeveloper.project2.domain.models.remote.ClientDetails
import com.gulfappdeveloper.project2.domain.models.remote.GetDataFromRemote
import com.gulfappdeveloper.project2.domain.models.remote.ProductDetails
import com.gulfappdeveloper.project2.usecases.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "RootViewModel"
@HiltViewModel
class RootViewModel @Inject constructor(
    private val useCase: UseCase
): ViewModel() {

    var operationCount = mutableStateOf(0)
        private set

    var baseUrl = mutableStateOf(HttpRoutes.BASE_URL)
        private set

    private val _productDetails: MutableStateFlow<List<ProductDetails>> =
        MutableStateFlow(emptyList())
    val productDetails: StateFlow<List<ProductDetails>> = _productDetails

    private val _clientDetails: MutableStateFlow<List<ClientDetails>> =
        MutableStateFlow(emptyList())
    val clientDetails: StateFlow<List<ClientDetails>> = _clientDetails


    init {
        saveOperationCount()
    }

    private fun saveOperationCount(){
        viewModelScope.launch {
            useCase.updateOperationCountUseCase()
            readOperationCount()
        }
    }

    private fun readOperationCount(){
        viewModelScope.launch {
            useCase.readOperationCountUseCase().collectLatest {
                Log.d(TAG, "readOperationCount: $it")
                operationCount.value =it
                readBaseUrl()
            }
        }
    }

    private fun readBaseUrl(){
        viewModelScope.launch {
            useCase.readBaseUrlUseCase().collectLatest {
                Log.i(TAG, "readBaseUrl: $it")
                baseUrl.value = it
                getProductDetails()
                getClientDetails()
            }
        }
    }

    private fun getProductDetails() {
        viewModelScope.launch {
            useCase.getProductDetailsUseCase(url =baseUrl.value+HttpRoutes.GET_PRODUCT_DETAILS).collectLatest { result ->
                Log.w(TAG, "getProductDetails: $result", )
                if (result is GetDataFromRemote.Success) {
                    _productDetails.value = result.data
                }
                if (result is GetDataFromRemote.Failed){
                    Log.e(TAG, "getProductDetails: ${result.error.code}, ${result.error.message} " )
                }
            }
        }
    }

    private fun getClientDetails() {
        viewModelScope.launch {
            useCase.getClientDetailsUseCase(url =baseUrl.value+HttpRoutes.GET_CLIENT_DETAILS).collectLatest { result ->
                Log.i(TAG, "getClientDetails: $result")
                if (result is GetDataFromRemote.Success) {
                    _clientDetails.value = result.data
                }
                if (result is GetDataFromRemote.Failed) {
                    Log.e(TAG, "getClientDetails: ${result.error.code}, ${result.error.message}")
                }
            }
        }
    }

}