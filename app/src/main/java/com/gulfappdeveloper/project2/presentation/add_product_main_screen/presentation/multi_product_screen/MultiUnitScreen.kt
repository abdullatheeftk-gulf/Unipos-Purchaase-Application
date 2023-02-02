package com.gulfappdeveloper.project2.presentation.add_product_main_screen.presentation.multi_product_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.presentation.add_product_main_screen.AddProductMainViewModel
import com.gulfappdeveloper.project2.presentation.add_product_main_screen.presentation.multi_product_screen.components.DataEntryArea
import com.gulfappdeveloper.project2.presentation.add_product_main_screen.presentation.multi_product_screen.components.ListArea
import kotlinx.coroutines.launch

@Composable
fun MultiUnitScreen(
    addProductMainViewModel: AddProductMainViewModel,
    addProductNavHostController: NavHostController,
    hideKeyboard:()->Unit
) {
    val scaffoldState = rememberScaffoldState()

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true){
        addProductMainViewModel.setUnitsForMultiUnitScreen()
    }

    BackHandler(true) {
        addProductMainViewModel.clearMultiUnitDataEntryArea()
        addProductNavHostController.popBackStack()
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Add Multi Unit")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        addProductMainViewModel.clearMultiUnitDataEntryArea()
                        addProductNavHostController.popBackStack()
                    }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },

            )
        }
    ) {
        it.calculateTopPadding()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 12.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ListArea(addProductMainViewModel = addProductMainViewModel)
            Spacer(modifier = Modifier.height(12.dp))
            DataEntryArea(
                addProductMainViewModel =addProductMainViewModel,
                hideKeyboard = hideKeyboard,
                onDataValidationError = {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("Enter required values")
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    addProductMainViewModel.clearMultiUnitDataEntryArea()
                    addProductNavHostController.popBackStack()
                }
            ) {
                Text(text = "Submit")
            }
            Spacer(modifier = Modifier.height(300.dp))
        }


    }

}

/*
@Preview(showBackground = true)
@Composable
fun MultiScreenPrev() {
    MultiUnitScreen(){

    }
}*/
