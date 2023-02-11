package com.gulfappdeveloper.project2.presentation.ui_util

import com.gulfappdeveloper.project2.domain.models.remote.get.Product

sealed class UiEvent {
    object ShowProgressBar : UiEvent()
    object CloseProgressBar : UiEvent()
    data class ShowAlertDialog(val message:String) : UiEvent()
   // object CloseAlertDialog : UiEvent()
    data class ShowToastMessage(val message: String) : UiEvent()
    data class ShowEmptyList(val value: Boolean) : UiEvent()
    data class ShowSnackBar(val message: String, val action: (() -> Unit)? = null) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    //object AnimateWithKeyBoard : UiEvent()
   // object AnimateBackWithKeyBoard : UiEvent()
    data class AddedProduct(val product: Product) : UiEvent()
    object ShowButton1 : UiEvent()
}
