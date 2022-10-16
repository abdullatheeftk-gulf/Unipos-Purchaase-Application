package com.gulfappdeveloper.project2.presentation.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.home_screen.components.*

private const val TAG = "HomeScreen"

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    hideKeyboard: () -> Unit,
    onScanButtonClicked: () -> Unit,
    rootViewModel: RootViewModel,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()


    val operationCount by rootViewModel.operationCount
    val baseUrl by rootViewModel.baseUrl
    val clientDetailsList = rootViewModel.clientDetails.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar()
        }
    ) {
        it.calculateTopPadding()
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(color = 0x93F1ECEC)),
                contentAlignment = Alignment.TopCenter
            ) {
                Text(
                    text = "Enter purchase details :",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 4.dp),
                    color = MaterialTheme.colors.error
                )
            }

            FirstThreeRows(onSelectDateClicked = {

            })

            Spacer(modifier = Modifier.height(10.dp))

            ProductListTitle()
            ProductList()
            ItemSelectionRows()
            ProductButtonRow()
            ProductPriceColumn()
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Submit")
            }


        }


    }

}