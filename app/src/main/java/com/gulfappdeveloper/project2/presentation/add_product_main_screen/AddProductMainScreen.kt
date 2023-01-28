package com.gulfappdeveloper.project2.presentation.add_product_main_screen

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.add_product_main_screen.navigation.AddProductNavGraph

@Composable
fun AddProductMainScreen(
    rootViewModel: RootViewModel,
    hideKeyboard: () -> Unit,
    navHostController: NavHostController
) {


    val addProductNavHostController = rememberNavController()


    AddProductNavGraph(
        rootViewModel = rootViewModel,
        navHostController = navHostController,
        addProductNavHostController = addProductNavHostController,
        hideKeyboard = hideKeyboard
    )


}