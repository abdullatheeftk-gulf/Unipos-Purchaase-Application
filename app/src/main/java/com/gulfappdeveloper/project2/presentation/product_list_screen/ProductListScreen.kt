package com.gulfappdeveloper.project2.presentation.product_list_screen


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.navigation.root.RootNavScreens
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.product_list_screen.components.product_list.EmptyList
import com.gulfappdeveloper.project2.presentation.product_list_screen.components.product_list.ShowProductList
import com.gulfappdeveloper.project2.presentation.product_list_screen.components.topbar.SearchTopBar
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Composable
fun ProductListScreen(
    rootViewModel: RootViewModel,
    hideKeyboard: () -> Unit,
    navHostController: NavHostController
) {

    val snackBarHostState = remember {
        SnackbarHostState()
    }

    var showEmptyList by remember {
        mutableStateOf(false)
    }
    var showProgressBar by remember {
        mutableStateOf(false)
    }

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        rootViewModel.resetNavCounter()
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
                    navHostController.popBackStack()
                    navHostController.navigate(route = RootNavScreens.PurchaseScreen.route)
                }
                is UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(event.message)
                }
                else -> Unit
            }
        }
    }
    BackHandler(true) {
        navHostController.popBackStack()
        navHostController.navigate(route = RootNavScreens.PurchaseScreen.route)
    }


    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        topBar = {
            SearchTopBar(
                rootViewModel = rootViewModel,
                onClearButtonClicked = {
                    rootViewModel.setProductName("", isItFromHomeScreen = false,requiredSearch = false)
                },
                hideKeyboard = hideKeyboard,
                onBackButtonClicked = {
                    navHostController.popBackStack()
                    navHostController.navigate(route = RootNavScreens.PurchaseScreen.route)
                },
                onSearchTextIsLessThanThree = {
                    scope.launch {
                        snackBarHostState.showSnackbar("Search text is less than 3")
                    }
                }
            )
        }
    ) {paddingValues->

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
                paddingValues = paddingValues
            )
        }


    }
}