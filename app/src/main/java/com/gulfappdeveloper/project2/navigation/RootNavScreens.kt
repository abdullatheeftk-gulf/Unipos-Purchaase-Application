package com.gulfappdeveloper.project2.navigation

sealed class RootNavScreens(val route: String) {
    object SplashScreen : RootNavScreens("splash_screen")
    object HomeScreen : RootNavScreens("home_screen")
    object ProductListScreen : RootNavScreens("product_list")
}