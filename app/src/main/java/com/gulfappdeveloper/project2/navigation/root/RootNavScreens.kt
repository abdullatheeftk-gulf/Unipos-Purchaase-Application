package com.gulfappdeveloper.project2.navigation.root

sealed class RootNavScreens(val route: String) {
    object SplashScreen : RootNavScreens("splash_screen")
    object UrlSetScreen : RootNavScreens("url_set_screen")
    object HomeScreen : RootNavScreens("home_screen")
    object ProductListScreen : RootNavScreens("product_list")
    object ClientListScreen : RootNavScreens("client_list")
}