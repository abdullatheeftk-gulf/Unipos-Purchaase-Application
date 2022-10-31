package com.gulfappdeveloper.project2.presentation.splash_screen.util

sealed class SplashScreenEvent{
    object ShowProgressBar : SplashScreenEvent()
    object CloseProgressBar:SplashScreenEvent()
    object ShowSetBaseUrlButton:SplashScreenEvent()
    data class ShowToast(val message:String):SplashScreenEvent()

}
