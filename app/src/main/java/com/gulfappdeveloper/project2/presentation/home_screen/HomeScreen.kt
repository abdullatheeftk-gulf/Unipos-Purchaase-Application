package com.gulfappdeveloper.project2.presentation.home_screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.navigation.root.RootViewModel


@Composable
fun HomeScreen(
    navHostController: NavHostController,
    onHideKeyBoard: () -> Unit,
    onScanButtonClicked: () -> Unit,
    rootViewModel: RootViewModel,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val productDetails by rootViewModel.productDetails.collectAsState()
    val clientDetails by rootViewModel.clientDetails.collectAsState()
    
    val operationCount by rootViewModel.operationCount

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomAppBar() {
                Text(text = "$operationCount")
            }
        }
    ) {
        it.calculateTopPadding()

        LazyColumn {
            items(productDetails) { product ->
                Text(text = product.productName)
            }

        }

    }

}