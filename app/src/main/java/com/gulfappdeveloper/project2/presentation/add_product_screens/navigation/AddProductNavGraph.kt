package com.gulfappdeveloper.project2.presentation.add_product_screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.add_product_screens.AddProductMainViewModel
import com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.add_product_home_screen.AddProductHomeScreen
import com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.multi_product_screen.MultiUnitScreen
import com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.select_product_group.SelectProductGroupScreen
import com.gulfappdeveloper.project2.presentation.ui_util.ScanFrom

@Composable
fun AddProductNavGraph(
    rootViewModel: RootViewModel,
    navHostController: NavHostController,
    addProductNavHostController: NavHostController,
    hideKeyboard: () -> Unit,
    addProductMainViewModel: AddProductMainViewModel = hiltViewModel(),
    onScanButtonClicked:(ScanFrom)->Unit
) {
    val addProductBarcodeScanned by rootViewModel.addProductBarcodeScanned
    val multiUnitBarcodeScanned by rootViewModel.multiUnitBarcodeScanned
    LaunchedEffect(key1 = addProductBarcodeScanned){
        addProductMainViewModel.setBarcode(addProductBarcodeScanned)
    }
    LaunchedEffect(key1 = multiUnitBarcodeScanned){
        addProductMainViewModel.setMultiUnitBarcode(multiUnitBarcodeScanned)
    }

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
                addProductMainViewModel = addProductMainViewModel,
                onScanButtonClicked = onScanButtonClicked
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
                addProductNavHostController = addProductNavHostController,
                onScanButtonClicked = onScanButtonClicked
            )
        }
    }
}