package com.gulfappdeveloper.project2.presentation.product_list_screen

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
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
                    Log.e(TAG, "ProductListScreen:")
                    navHostController.popBackStack()
                    navHostController.navigate(route = RootNavScreens.PurchaseScreen.route)
                    /*navHostController.navigate(route = RootNavScreens.PurchaseScreen.route){
                        popUpTo(RootNavScreens.PurchaseScreen.route){
                            inclusive = true
                        }
                    }*/
                }
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
                else -> Unit
            }
        }
    }
    BackHandler(true) {
        navHostController.popBackStack()
        navHostController.navigate(route = RootNavScreens.PurchaseScreen.route)
        /*navHostController.navigate(route = RootNavScreens.PurchaseScreen.route){
            popUpTo(RootNavScreens.PurchaseScreen.route){
                inclusive = true
            }
        }*/
    }




    Scaffold(
        scaffoldState = scaffoldState,
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
                        scaffoldState.snackbarHostState.showSnackbar("Search text is less than 3")
                    }
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