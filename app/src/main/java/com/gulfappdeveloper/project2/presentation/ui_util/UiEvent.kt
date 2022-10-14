package com.gulfappdeveloper.project2.presentation.ui_util

sealed class UiEvent{
    object ShowProgressBar:UiEvent()
    object CloseProgressBar:UiEvent()
    object ShowAlertDialog:UiEvent()
    object CloseAlertDialog:UiEvent()
    data class ShowToastMessage(val message:String):UiEvent()
    object ShowEmptyList:UiEvent()
    data class ShowSnackBar(val message:String,val action:(()->Unit)? = null):UiEvent()
    data class Navigate(val route:String):UiEvent()
}
