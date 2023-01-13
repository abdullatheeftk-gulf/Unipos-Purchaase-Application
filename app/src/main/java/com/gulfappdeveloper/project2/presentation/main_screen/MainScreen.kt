package com.gulfappdeveloper.project2.presentation.main_screen

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.navigation.root.RootViewModel

@Composable
fun MainScreen(
    rootViewModel: RootViewModel,
    navHostController: NavHostController
) {
    val scaffoldState = rememberScaffoldState()


    Scaffold(scaffoldState = scaffoldState) {
        it.calculateTopPadding()



    }

}