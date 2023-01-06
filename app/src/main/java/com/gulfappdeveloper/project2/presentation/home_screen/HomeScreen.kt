package com.gulfappdeveloper.project2.presentation.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.navigation.root.RootNavScreens
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.home_screen.components.*
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import com.gulfappdeveloper.project2.presentation.ui_util.keyboardAsState
import com.gulfappdeveloper.project2.ui.theme.OrangeColor
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
    val lazyColumState = rememberLazyListState()

    val selectedProductList = rootViewModel.selectedProductList

    val coroutineScope = rememberCoroutineScope()


    val listState = rememberLazyListState()

    val isKeyBoardOpen by keyboardAsState()

    var showCalendar by remember {
        mutableStateOf(false)
    }
    var showProgressBar by remember {
        mutableStateOf(false)
    }

    var showAddClientDialog by remember {
        mutableStateOf(false)
    }

    var showProductNameError by remember {
        mutableStateOf(false)
    }

    var showQuantityError by remember {
        mutableStateOf(false)
    }

    if (showAddClientDialog) {
        AddClientDialog(
            rootViewModel = rootViewModel,
            onDismissRequest = {
                showAddClientDialog = false
            }
        )
    }

    LaunchedEffect(key1 = true) {
        homeScreenViewModel.uiEvent.collectLatest { value ->
            when (value) {
                is UiEvent.AnimateWithKeyBoard -> {
                    // Log.d(TAG, "event: AnimateWithKeyBoard ")
                    listState.animateScrollToItem(2)
                }
                is UiEvent.AnimateBackWithKeyBoard -> {
                    // Log.e(TAG, "event: AnimateBackWithKeyBoard ")
                    listState.animateScrollToItem(0)
                }
                else -> Unit
            }
        }
    }

    LaunchedEffect(key1 = isKeyBoardOpen,
        block = {
            if (isKeyBoardOpen.name == "Closed") {
                listState.scrollToItem(0)
            }
        }
    )

    LaunchedEffect(key1 = true) {
        rootViewModel.homeScreenEvent.collectLatest { value ->
            when (val event = value.uiEvent) {
                is UiEvent.ShowProgressBar -> {
                    showProgressBar = true
                }
                is UiEvent.CloseProgressBar -> {
                    showProgressBar = false
                }
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(message = event.message)
                }
                is UiEvent.Navigate -> {
                    navHostController.navigate(route = event.route)
                }
                else -> Unit
            }

        }
    }


    // Log.d(TAG, "HomeScreen: ${isKeyBoardOpen.name}")


    if (showCalendar) {
        CalendarDialog(
            rootViewModel = rootViewModel,
            onDismissRequest = {
                showCalendar = false
            }
        )


    }



    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar()
        }
    ) {
        it.calculateTopPadding()



        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState
        ) {
            item {
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
                        color = MaterialTheme.colors.OrangeColor
                    )
                }

            }

            item {
                FirstThreeRows(
                    rootViewModel = rootViewModel,
                    hideKeyboard = hideKeyboard,
                    onSelectDateClicked = {
                        showCalendar = true
                    },
                    navHostController = navHostController,
                    onAddClientClicked = {
                        //showAddClientDialog = true
                        navHostController.navigate(route = RootNavScreens.AddClientScreen.route)
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                ProductListTitle()
            }
            item {
                ProductList(
                    rootViewModel = rootViewModel,
                    lazyColumnState = lazyColumState
                )
            }
            item {

                ItemSelectionRows(
                    rootViewModel = rootViewModel,
                    navHostController = navHostController,
                    hideKeyboard = hideKeyboard,
                    onQrScanClicked = onScanButtonClicked,
                    showProductNameError = showProductNameError,
                    showQuantityError = showQuantityError,
                    onProductNameError = {
                        showProductNameError = false
                    },
                    onQuantityError = {
                        showQuantityError = false
                    },
                )
            }
            item {
                ProductButtonRow(
                    rootViewModel = rootViewModel,
                    onProductAdded = {
                        rootViewModel.addToProductList()
                        coroutineScope.launch {
                            if (selectedProductList.size > 3) {
                                lazyColumState.scrollToItem(selectedProductList.size - 1)
                            }
                        }
                    },
                    onProductNameError = {
                        showProductNameError = true
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(message = "Product is not selected")
                        }
                    },
                    onQuantityError = {
                        showQuantityError = true
                    },
                    onBarcodeError = {
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(message = "Product is not selected")
                        }
                    }
                )
            }
            item {
                ProductPriceColumn()
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                Button(onClick = {


                }) {
                    Text(text = "Submit")
                }
            }
            item {
                Spacer(modifier = Modifier.height(300.dp))
            }
        }

        if (showProgressBar) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }


    }

}