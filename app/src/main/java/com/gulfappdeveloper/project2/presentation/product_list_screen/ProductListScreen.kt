package com.gulfappdeveloper.project2.presentation.product_list_screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.product_list_screen.components.product_list.EmptyList
import com.gulfappdeveloper.project2.presentation.product_list_screen.components.product_list.ShowProductList
import com.gulfappdeveloper.project2.presentation.product_list_screen.components.topbar.SearchTopBar
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import kotlinx.coroutines.flow.collectLatest

private const val TAG = "ProductListScreen"

@Composable
fun ProductListScreen(
    rootViewModel: RootViewModel,
    hideKeyboard: () -> Unit,
    navHostController: NavHostController
) {

    val scaffoldState = rememberScaffoldState()

    var showEmptyList by remember {
        mutableStateOf(false)
    }
    var showProgressBar by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        rootViewModel.productListScreenEvent.collectLatest { value ->
            when (val event = value.uiEvent) {
                is UiEvent.ShowProgressBar -> {
                    showProgressBar = true
                }
                is UiEvent.CloseProgressBar -> {
                    showProgressBar = false
                }
                is UiEvent.ShowEmptyList -> {
                    showEmptyList = event.value
                }
                is UiEvent.Navigate -> {
                    Log.e(TAG, "ProductListScreen:")
                    navHostController.popBackStack()
                }
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
                else -> Unit
            }
        }
    }




    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SearchTopBar(
                rootViewModel = rootViewModel,
                onClearButtonClicked = {
                    rootViewModel.setProductName("", isItFromHomeScreem = false)
                },
                hideKeyboard = hideKeyboard,
                onBackButtonClicked = {
                    navHostController.popBackStack()
                }
            )
        }
    ) {
        it.calculateTopPadding()
        if (showProgressBar) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            return@Scaffold
        }

        if (showEmptyList) {
            EmptyList()
        } else {
            ShowProductList(
                rootViewModel = rootViewModel,
            )
        }


    }
}