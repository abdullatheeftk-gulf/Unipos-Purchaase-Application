package com.gulfappdeveloper.project2.presentation.home_screen

import android.util.Log
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
import com.gulfappdeveloper.project2.domain.models.remote.get.ProductDetails
import com.gulfappdeveloper.project2.navigation.root.RootViewModel

private const val TAG = "HomeScreen"
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    onHideKeyBoard: () -> Unit,
    onScanButtonClicked: () -> Unit,
    rootViewModel: RootViewModel,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()


    Log.d(TAG, "HomeScreen: ")
    
    val operationCount by rootViewModel.operationCount
    val baseUrl by rootViewModel.baseUrl
    val clientDetailsList = rootViewModel.clientDetails.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomAppBar() {
                Text(operationCount.toString())
            }
        }
    ) {
        it.calculateTopPadding()

        LazyColumn {
            items(clientDetailsList.value){items->
                Text(text = items.clientName)
            }

        }

    }

}