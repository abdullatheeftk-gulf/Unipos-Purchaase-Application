package com.gulfappdeveloper.project2.navigation.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gulfappdeveloper.project2.presentation.add_client_screen.AddClientScreen
import com.gulfappdeveloper.project2.presentation.add_product_screens.AddProductMainScreen
import com.gulfappdeveloper.project2.presentation.client_screen.ClientListScreen
import com.gulfappdeveloper.project2.presentation.purchase_screen.PurchaseScreen
import com.gulfappdeveloper.project2.presentation.login_screen.LoginScreen
import com.gulfappdeveloper.project2.presentation.main_screen.MainScreen
import com.gulfappdeveloper.project2.presentation.price_adjustment_screens.adjust_price_screen.AdjustPriceScreen
import com.gulfappdeveloper.project2.presentation.price_adjustment_screens.showProductsForPriceAdjustmentScreens.ShowProductsForPriceAdjustmentScreen
import com.gulfappdeveloper.project2.presentation.product_list_screen.ProductListScreen
import com.gulfappdeveloper.project2.presentation.set_base_url_screen.SetBaseUrlScreen
import com.gulfappdeveloper.project2.presentation.settings_screen.SettingsScreen
import com.gulfappdeveloper.project2.presentation.splash_screen.SplashScreen
import com.gulfappdeveloper.project2.presentation.stock_adjustment_screen.StockAdjustmentScreen
import com.gulfappdeveloper.project2.presentation.ui_util.ScanFrom
import com.gulfappdeveloper.project2.presentation.uni_licence_act_screen.UniLicenseActivationScreen


@Composable
fun RootNavGraph(
    navHostController: NavHostController,
    hideKeyboard: () -> Unit,
    onScanButtonClicked: (ScanFrom) -> Unit,
    rootViewModel: RootViewModel,
    deviceId: String
) {

    NavHost(
        navController = navHostController,
        startDestination = RootNavScreens.SplashScreen.route
    ) {


        composable(RootNavScreens.SplashScreen.route) {
            SplashScreen(
                navHostController = navHostController,
                rootViewModel = rootViewModel,
                deviceId = deviceId
            )
        }


        composable(RootNavScreens.UrlSetScreen.route) {
            SetBaseUrlScreen(
                rootViewModel = rootViewModel,
                navHostController = navHostController,
                hideKeyboard = hideKeyboard
            )
        }

        composable(RootNavScreens.UniLicenseActivationScreen.route) {
            UniLicenseActivationScreen(
                rootViewModel = rootViewModel,
                navHostController = navHostController,
                hideKeyboard = hideKeyboard,
                deviceId = deviceId
            )
        }

        composable(route = RootNavScreens.LoginScreen.route) {
            LoginScreen(
                rootViewModel = rootViewModel,
                navHostController = navHostController,
                hideKeyboard = hideKeyboard
            )
        }

        composable(RootNavScreens.MainScreen.route) {
            MainScreen(
                rootViewModel = rootViewModel,
                navHostController = navHostController
            )
        }

        composable(RootNavScreens.PurchaseScreen.route) {
            PurchaseScreen(
                navHostController = navHostController,
                hideKeyboard = hideKeyboard,
                onScanButtonClicked = onScanButtonClicked,
                rootViewModel = rootViewModel,
            )
        }

        composable(route = RootNavScreens.StockAdjustmentScreen.route) {
            StockAdjustmentScreen(
                rootViewModel = rootViewModel,
                navHostController = navHostController,
                hideKeyboard = hideKeyboard,
                onScanButtonClicked = onScanButtonClicked
            )
        }

        composable(route = RootNavScreens.ShowProductForPriceAdjustmentScreen.route) {
            ShowProductsForPriceAdjustmentScreen(
                rootViewModel = rootViewModel,
                navHostController = navHostController,
                onScanButtonClicked = onScanButtonClicked,
                hideKeyboard = hideKeyboard
            )
        }

        composable(route = RootNavScreens.AdjustPriceScreen.route){
            AdjustPriceScreen(
                rootViewModel = rootViewModel,
                navHostController = navHostController,
                hideKeyboard = hideKeyboard
            )
        }

        composable(route = RootNavScreens.SettingsScreen.route) {
            SettingsScreen(
                rootViewModel = rootViewModel,
                hideKeyboard = hideKeyboard,
                navHostController = navHostController
            )
        }

        composable(RootNavScreens.AddClientScreen.route) {
            AddClientScreen(
                rootViewModel = rootViewModel,
                hideKeyboard = hideKeyboard,
                navHostController = navHostController
            )
        }

        composable(route = RootNavScreens.ProductListScreen.route) {
            ProductListScreen(
                rootViewModel = rootViewModel,
                navHostController = navHostController,
                hideKeyboard = hideKeyboard,
            )
        }

        composable(route = RootNavScreens.ClientListScreen.route) {
            ClientListScreen(
                rootViewModel = rootViewModel,
                navHostController = navHostController,
                hideKeyboard = hideKeyboard
            )
        }

        composable(route = RootNavScreens.AddProductMainScreen.route) {
            AddProductMainScreen(
                rootViewModel = rootViewModel,
                hideKeyboard = hideKeyboard,
                navHostController = navHostController,
                onScanButtonClicked = onScanButtonClicked
            )
        }


    }
}