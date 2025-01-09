package com.gulfappdeveloper.project2.presentation.add_product_screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.add_product_screens.navigation.AddProductNavGraph
import com.gulfappdeveloper.project2.presentation.ui_util.ScanFrom

@Composable
fun AddProductMainScreen(
    rootViewModel: RootViewModel,
    hideKeyboard: () -> Unit,
    navHostController: NavHostController,
    onScanButtonClicked:(ScanFrom)->Unit
) {


    val addProductNavHostController = rememberNavController()




    AddProductNavGraph(
        rootViewModel = rootViewModel,
        navHostController = navHostController,
        addProductNavHostController = addProductNavHostController,
        hideKeyboard = hideKeyboard,
        onScanButtonClicked = onScanButtonClicked
    )


}