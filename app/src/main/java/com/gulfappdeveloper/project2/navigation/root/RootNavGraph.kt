package com.gulfappdeveloper.project2.navigation.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gulfappdeveloper.project2.presentation.add_client_screen.AddClientScreen
import com.gulfappdeveloper.project2.presentation.add_product_main_screen.AddProductMainScreen
import com.gulfappdeveloper.project2.presentation.client_screen.ClientListScreen
import com.gulfappdeveloper.project2.presentation.home_screen.HomeScreen
import com.gulfappdeveloper.project2.presentation.product_list_screen.ProductListScreen
import com.gulfappdeveloper.project2.presentation.set_base_url_screen.SetBaseUrlScreen
import com.gulfappdeveloper.project2.presentation.splash_screen.SplashScreen


@Composable
fun RootNavGraph(
    navHostController: NavHostController,
    hideKeyboard: () -> Unit,
    onScanButtonClicked: () -> Unit,
    rootViewModel: RootViewModel,
) {


    NavHost(
        navController = navHostController,
        startDestination = RootNavScreens.HomeScreen.route
    ) {


        composable(RootNavScreens.SplashScreen.route) {
            SplashScreen(
                navHostController = navHostController,
                rootViewModel = rootViewModel
            )
        }

        composable(RootNavScreens.UrlSetScreen.route) {
            SetBaseUrlScreen(
                rootViewModel = rootViewModel,
                navHostController = navHostController,
                hideKeyboard = hideKeyboard
            )
        }

        composable(RootNavScreens.HomeScreen.route) {
            HomeScreen(
                navHostController = navHostController,
                hideKeyboard = hideKeyboard,
                onScanButtonClicked = onScanButtonClicked,
                rootViewModel = rootViewModel,
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
               navHostController = navHostController
           )
        }



    }
}