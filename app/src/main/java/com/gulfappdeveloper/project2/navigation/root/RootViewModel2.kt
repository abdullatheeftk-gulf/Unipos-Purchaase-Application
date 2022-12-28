package com.gulfappdeveloper.project2.navigation.root

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.gulfappdeveloper.project2.data.remote.HttpRoutes
import com.gulfappdeveloper.project2.domain.models.remote.get.GetDataFromRemote
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import com.gulfappdeveloper.project2.usecases.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "RootViewModel2"
@HiltViewModel
class RootViewModel2
@Inject constructor(
    private val useCase: UseCase
) : RootViewModel(useCase = useCase) {



   open fun searchProductListByName() {
        sendProductListScreenEvent(UiEvent.ShowProgressBar)
        val url = baseUrl.value + HttpRoutes.GET_PRODUCT_DETAILS + _productSearchText.value
        try {
            productList.removeAll {
                true
            }
        } catch (e: Exception) {
            Log.e(TAG, "getProductDetails: ${e.message}")
        }
        viewModelScope.launch {
            useCase.getProductDetailsUseCase(url = url)
                .collectLatest { result ->
                    sendProductListScreenEvent(UiEvent.CloseProgressBar)
                    Log.w(TAG, "getProductDetails: $result")
                    if (result is GetDataFromRemote.Success) {
                        if (result.data.isEmpty()) {
                            sendProductListScreenEvent(UiEvent.ShowEmptyList(value = true))
                        } else {
                            try {
                                productList.removeAll {
                                    true
                                }
                            } catch (e: Exception) {
                                Log.e(TAG, "getProductDetails: ${e.message}")
                            }
                            productList.addAll(result.data)
                            sendProductListScreenEvent(UiEvent.ShowEmptyList(value = false))
                        }
                    }
                    if (result is GetDataFromRemote.Failed) {
                        sendProductListScreenEvent(UiEvent.ShowEmptyList(value = true))
                        sendProductListScreenEvent(UiEvent.ShowSnackBar(message = "url:- $url, code:- ${result.error.code}, error: ${result.error.message}"))
                        Log.e(
                            TAG,
                            "getProductDetails: ${result.error.code}, ${result.error.message} "
                        )
                    }
                }
        }
    }

    fun searchProductByQrCode(value: String) {
        sendHomeScreenEvent(UiEvent.ShowProgressBar)
        val url = baseUrl.value + HttpRoutes.PRODUCT_SEARCH_BY_BARCODE + value
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getProductDetailByBarcodeUseCase(url = url).collectLatest {result->
                sendHomeScreenEvent(UiEvent.CloseProgressBar)
                if (result is GetDataFromRemote.Success){
                    Log.e(TAG, "searchProductByQrCode: ${result.data}", )
                    result.data?.let {product->
                        _productSearchText.value = product.productName
                        setSelectedProduct(product)
                        return@collectLatest
                    }
                    sendHomeScreenEvent(UiEvent.ShowSnackBar("No item with barcode $value"))
                }
                if (result is GetDataFromRemote.Failed){
                    sendHomeScreenEvent(UiEvent.ShowToastMessage("There have error when scanning ${result.error.message}"))
                    Log.e(TAG, "searchProductByQrCode: ${result.error}", )
                }
            }

        }
    }

    fun setProductListEvent(event: UiEvent) {
        sendProductListScreenEvent(uiEvent = event)
    }
}