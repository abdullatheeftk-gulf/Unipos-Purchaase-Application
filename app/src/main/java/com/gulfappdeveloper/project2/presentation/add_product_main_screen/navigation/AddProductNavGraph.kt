package com.gulfappdeveloper.project2.presentation.add_product_main_screen.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.add_product_main_screen.AddProductMainViewModel
import com.gulfappdeveloper.project2.presentation.add_product_main_screen.presentation.add_product_home_screen.AddProductHomeScreen
import com.gulfappdeveloper.project2.presentation.add_product_main_screen.presentation.multi_product_screen.MultiUnitScreen
import com.gulfappdeveloper.project2.presentation.add_product_main_screen.presentation.select_product_group.SelectProductGroupScreen

private const val TAG = "AddProductNavGraph"
@Composable
fun AddProductNavGraph(
    rootViewModel: RootViewModel,
    navHostController: NavHostController,
    addProductNavHostController: NavHostController,
    hideKeyboard: () -> Unit,
    addProductMainViewModel: AddProductMainViewModel = hiltViewModel()
) {


    NavHost(
        navController = addProductNavHostController,
        startDestination = AddProductScreens.AddProductHomeScreen.route
    ) {
        composable(route = AddProductScreens.AddProductHomeScreen.route) {
            AddProductHomeScreen(
                rootViewModel = rootViewModel,
                navHostController = navHostController,
                addProductNavHostController = addProductNavHostController,
                hideKeyboard = hideKeyboard,
                addProductMainViewModel = addProductMainViewModel
            )
        }
        composable(route = AddProductScreens.SelectProductGroupScreen.route) {
            SelectProductGroupScreen(
                hideKeyboard = hideKeyboard,
                addProductMainViewModel = addProductMainViewModel,
                addProductNavHostController = addProductNavHostController
            )
        }
        composable(route = AddProductScreens.MultiUnitScreen.route){
            MultiUnitScreen(
                hideKeyboard = hideKeyboard,
                addProductMainViewModel = addProductMainViewModel,
                addProductNavHostController = addProductNavHostController
            )
        }
    }
}