package com.gulfappdeveloper.project2.presentation.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.domain.models.product_selected.ProductSelected
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

    /* var showAddClientDialog by remember {
         mutableStateOf(false)
     }*/

    var showProductNameError by remember {
        mutableStateOf(false)
    }

    var showQuantityError by remember {
        mutableStateOf(false)
    }

    var showListEditAlertDialog by remember {
        mutableStateOf(false)
    }

    var productSelectedForList: ProductSelected? by remember {
        mutableStateOf(null)
    }

    var countSelectedForList by remember {
        mutableStateOf(-1)
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

    if (showListEditAlertDialog) {
        productSelectedForList?.let {
            if (countSelectedForList>=0) {
                ListEditAlertDialog(
                    rootViewModel = rootViewModel,
                    productSelected = it,
                    onDismissRequest = {
                        showListEditAlertDialog = false
                    },
                    count = countSelectedForList
                )
            }
        }
    }



    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar()
        }
    ) {
        it.calculateTopPadding()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
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
                    color = MaterialTheme.colors.OrangeColor
                )
            }




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


            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(horizontal = 12.dp),
                elevation = 4.dp
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    ProductListTitle()
                    Spacer(modifier = Modifier.height(4.dp))
                    ProductList(
                        rootViewModel = rootViewModel,
                        onProductListItemClicked = { i, productSelected ->
                            productSelectedForList = productSelected
                            countSelectedForList = i
                            showListEditAlertDialog = true
                        }
                    )
                }
            }



            Spacer(modifier = Modifier.height(8.dp))


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
                    showProductNameError = false
                },
            )


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


            ProductPriceColumn()


            Spacer(modifier = Modifier.height(10.dp))


            Button(onClick = {
                // ToDo
            }) {
                Text(text = "Submit")
            }

            Spacer(modifier = Modifier.height(300.dp))

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