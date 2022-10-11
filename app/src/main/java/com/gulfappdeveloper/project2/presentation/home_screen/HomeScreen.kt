package com.gulfappdeveloper.project2.presentation.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController


@Composable
fun HomeScreen(
    navHostController: NavHostController,
    onHideKeyBoard: () -> Unit,
    onScanButtonClicked: () -> Unit,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val productDetails by homeScreenViewModel.productDetails.collectAsState()
    val clientDetails by homeScreenViewModel.clientDetails.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        it.calculateTopPadding()

        LazyColumn{
            /*items(productDetails){product->
                Text(text = product.productName)
            }*/
            items(clientDetails){client->
                Text(text = client.clientName)
            }
        }

    }

}