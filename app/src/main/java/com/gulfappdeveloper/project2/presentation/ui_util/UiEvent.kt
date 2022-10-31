package com.gulfappdeveloper.project2.presentation.ui_util

import com.gulfappdeveloper.project2.presentation.splash_screen.util.SplashScreenEvent

sealed class UiEvent{
    object ShowProgressBar:UiEvent()
    object CloseProgressBar:UiEvent()
    object ShowAlertDialog:UiEvent()
    object CloseAlertDialog:UiEvent()
    data class ShowToastMessage(val message:String):UiEvent()
    object ShowEmptyList:UiEvent()
    data class ShowSnackBar(val message:String,val action:(()->Unit)? = null):UiEvent()
    data class Navigate(val route:String):UiEvent()
    object AnimateWithKeyBoard: UiEvent()
    object AnimateBackWithKeyBoard: UiEvent()
}
