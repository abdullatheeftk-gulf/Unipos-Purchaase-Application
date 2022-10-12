package com.gulfappdeveloper.project2.navigation.root

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gulfappdeveloper.project2.presentation.home_screen.HomeScreen


@Composable
fun RootNavGraph(
    navHostController: NavHostController,
    onHideKeyBoard:()->Unit,
    onScanButtonClicked:()->Unit,
    rootViewModel: RootViewModel = hiltViewModel()
) {

    NavHost(
        navController = navHostController,
        startDestination = RootNavScreens.HomeScreen.route
    ){

        composable(RootNavScreens.HomeScreen.route){
            HomeScreen(
                navHostController =navHostController,
                onHideKeyBoard = onHideKeyBoard,
                onScanButtonClicked = onScanButtonClicked,
                rootViewModel = rootViewModel
            )
        }

    }
}