package com.gulfappdeveloper.project2.navigation.root

sealed class RootNavScreens(val route: String) {
    data object SplashScreen : RootNavScreens("splash_screen")
    data object SplashScreen2 : RootNavScreens("splash_screen2")
    data object UrlSetScreen : RootNavScreens("url_set_screen")
    data object UniLicenseActivationScreen : RootNavScreens("uni_license_activation_screen")
    data object LoginScreen : RootNavScreens("login_screen")
    data object MainScreen : RootNavScreens("main_screen")
    data object PurchaseScreen : RootNavScreens("purchase_screen")
    data object StockAdjustmentScreen : RootNavScreens("stock_adjustment_screen")
    data object ShowProductForPriceAdjustmentScreen : RootNavScreens("show_product_for_price_adjustment")
    data object AdjustPriceScreen : RootNavScreens("adjust_price_screen")
    data object SettingsScreen : RootNavScreens("settings_screen")
    data object AddClientScreen : RootNavScreens("add_client_screen")
    data object ProductListScreen : RootNavScreens("product_list")
    data object ClientListScreen : RootNavScreens("client_list")
    data object AddProductMainScreen : RootNavScreens("add_product_screens")
    data object  PrintBarcodeScreen : RootNavScreens("print_barcode_screen")
    data object  SalesScreen : RootNavScreens("sales_screen")
    data object  SalesReturnScreen : RootNavScreens("sales_return_screen")
}