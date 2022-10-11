package com.gulfappdeveloper.project2.presentation.home_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulfappdeveloper.project2.domain.models.ClientDetails
import com.gulfappdeveloper.project2.domain.models.ProductDetails
import com.gulfappdeveloper.project2.usecases.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

//private const val TAG = "HomeScreenViewModel"
@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val useCase: UseCase
):ViewModel() {

    private val _productDetails:MutableStateFlow<List<ProductDetails>>  = MutableStateFlow(emptyList())
    val productDetails:StateFlow<List<ProductDetails>> = _productDetails

    private val _clientDetails:MutableStateFlow<List<ClientDetails>> = MutableStateFlow(emptyList())
    val clientDetails:StateFlow<List<ClientDetails>> = _clientDetails

    init {
        getProductDetails()
        getClientDetails()
    }

    private fun getProductDetails(){
        viewModelScope.launch {
            useCase.getProductDetailsUseCase().collectLatest { listOfProducts->
               // Log.i(TAG, "getProductDetails: $listOfProducts")
                _productDetails.value = listOfProducts
            }
        }
    }

    private fun getClientDetails(){
        viewModelScope.launch {
            useCase.getClientDetailsUseCase().collectLatest { listOfClient->
                _clientDetails.value = listOfClient
            }
        }
    }

}