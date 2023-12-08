package com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.multi_product_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.presentation.add_product_screens.AddProductMainViewModel
import com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.multi_product_screen.components.DataEntryArea
import com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.multi_product_screen.components.ListArea
import com.gulfappdeveloper.project2.presentation.ui_util.ScanFrom
import com.gulfappdeveloper.project2.ui.theme.OrangeColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiUnitScreen(
    addProductMainViewModel: AddProductMainViewModel,
    addProductNavHostController: NavHostController,
    hideKeyboard:()->Unit,
    onScanButtonClicked:(ScanFrom)->Unit
) {
    val snackBarHostState = remember {
        SnackbarHostState()
    }

    val scope = rememberCoroutineScope()



    LaunchedEffect(key1 = true){
        addProductMainViewModel.setUnitsForMultiUnitScreen()
    }

    BackHandler(true) {
        addProductMainViewModel.clearMultiUnitDataEntryArea()
        addProductNavHostController.popBackStack()
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        topBar = {
            TopAppBar(
                modifier = Modifier.shadow(elevation = 6.dp),
                title = {
                    Text(text = "Add Multi Unit", color = OrangeColor)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        addProductMainViewModel.clearMultiUnitDataEntryArea()
                        addProductNavHostController.popBackStack()
                    }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = OrangeColor
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        addProductMainViewModel.clearMultiUnitDataEntryArea()
                        addProductNavHostController.popBackStack()
                    }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = null,
                        )
                    }
                }

            )
        }
    ) {paddingValues->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 0.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(paddingValues.calculateTopPadding()))
            ListArea(addProductMainViewModel = addProductMainViewModel)
            Spacer(modifier = Modifier.height(12.dp))
            DataEntryArea(
                addProductMainViewModel =addProductMainViewModel,
                hideKeyboard = hideKeyboard,
                onDataValidationError = {
                    scope.launch {
                        snackBarHostState.showSnackbar("Enter required values")
                    }
                },
                onScanButtonClicked = onScanButtonClicked
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    addProductMainViewModel.clearMultiUnitDataEntryArea()
                    addProductNavHostController.popBackStack()
                }
            ) {
                Text(text = "OK")
            }
            Spacer(modifier = Modifier.height(300.dp))
        }


    }

}

