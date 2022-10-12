package com.gulfappdeveloper.project2.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

private const val TAG = "MainScreenViewModel"
@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val useCase: UseCase
): ViewModel() {

    private val _productDetails: MutableStateFlow<List<ProductDetails>> =
        MutableStateFlow(emptyList())
    val productDetails: StateFlow<List<ProductDetails>> = _productDetails

    private val _clientDetails: MutableStateFlow<List<ClientDetails>> =
        MutableStateFlow(emptyList())
    val clientDetails: StateFlow<List<ClientDetails>> = _clientDetails

    init {
        getProductDetails()
        getClientDetails()
    }

    private fun getProductDetails() {
        viewModelScope.launch {
            useCase.getProductDetailsUseCase().collectLatest { result ->
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
            useCase.getClientDetailsUseCase().collectLatest { result ->
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