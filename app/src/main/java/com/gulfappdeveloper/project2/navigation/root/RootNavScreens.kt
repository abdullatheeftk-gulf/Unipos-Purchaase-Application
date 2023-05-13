package com.gulfappdeveloper.project2.navigation.root

sealed class RootNavScreens(val route: String) {
    object SplashScreen : RootNavScreens("splash_screen")
    object SplashScreen2 : RootNavScreens("splash_screen2")
    object UrlSetScreen : RootNavScreens("url_set_screen")
    object UniLicenseActivationScreen : RootNavScreens("uni_license_activation_screen")
    object LoginScreen : RootNavScreens("login_screen")
    object MainScreen : RootNavScreens("main_screen")
    object PurchaseScreen : RootNavScreens("purchase_screen")
    object StockAdjustmentScreen : RootNavScreens("stock_adjustment_screen")
    object ShowProductForPriceAdjustmentScreen : RootNavScreens("show_product_for_price_adjustment")
    object AdjustPriceScreen : RootNavScreens("adjust_price_screen")
    object SettingsScreen : RootNavScreens("settings_screen")
    object AddClientScreen : RootNavScreens("add_client_screen")
    object ProductListScreen : RootNavScreens("product_list")
    object ClientListScreen : RootNavScreens("client_list")
    object AddProductMainScreen : RootNavScreens("add_product_screens")
}