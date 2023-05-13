package com.gulfappdeveloper.project2.presentation.purchase_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.booleanResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.R
import com.gulfappdeveloper.project2.domain.models.product_selected.ProductSelected
import com.gulfappdeveloper.project2.navigation.root.RootNavScreens
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.purchase_screen.components.*
import com.gulfappdeveloper.project2.presentation.ui_util.ScanFrom
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Composable
fun PurchaseScreen(
    navHostController: NavHostController,
    hideKeyboard: () -> Unit,
    onScanButtonClicked: (ScanFrom) -> Unit,
    rootViewModel: RootViewModel,
) {
    val scope = rememberCoroutineScope()


    val scaffoldState = rememberScaffoldState()
    val lazyColumState = rememberLazyListState()
    val scrollState = rememberScrollState()

    val fManager = LocalFocusManager.current

    val selectedProductList = rootViewModel.selectedProductList

    val coroutineScope = rememberCoroutineScope()

    val additionalDiscount by rootViewModel.additionalDiscount

    val isCashPurchase by rootViewModel.isCashPurchase


    var showCalendar by remember {
        mutableStateOf(false)
    }
    var showProgressBar by remember {
        mutableStateOf(false)
    }


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

    val showAdditionalDiscount by rootViewModel.showAdditionalDiscount

    var showSubmitAlertDialog by remember {
        mutableStateOf(false)
    }

    val freightCharge by rootViewModel.freightCharge

    var showBillNoError by remember {
        mutableStateOf(false)
    }

    var showClientError by remember {
        mutableStateOf(false)
    }

    val showFreightCharge by rootViewModel.showFreightCharge

    var showProductListIsEmpty by remember {
        mutableStateOf(false)
    }




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
                    if (event.route == RootNavScreens.ProductListScreen.route) {
                        navHostController.popBackStack()
                    }
                    navHostController.navigate(route = event.route) {
                    }
                }
                is UiEvent.ShowAlertDialog -> {
                    showSubmitAlertDialog = true
                }
                else -> Unit
            }

        }
    }


    if (showCalendar) {
        CalendarDialog(
            rootViewModel = rootViewModel,
            onDismissRequest = {
                showCalendar = false
                fManager.clearFocus()
            }
        )
    }

    if (showListEditAlertDialog) {
        productSelectedForList?.let {
            if (countSelectedForList >= 0) {
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

    if (showSubmitAlertDialog) {
        SubmitAlertDialog {
            showSubmitAlertDialog = false
            rootViewModel.setShowAdditionalDiscount(false)
            rootViewModel.setShowFreightCharge(false)
            rootViewModel.resetAll()
            scope.launch {
                scrollState.scrollTo(0)
            }
            fManager.clearFocus()
        }
    }

    if (selectedProductList.isNotEmpty()) {
        showProductListIsEmpty = false
    }

    BackHandler(true) {
        rootViewModel.resetAll()
        navHostController.popBackStack()
    }



    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                navHostController = navHostController,
                rootViewModel = rootViewModel
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            val billNo by rootViewModel.billNo
            val selectedClient by rootViewModel.selectedClient
            Button(
                onClick = {
                    if (selectedProductList.isEmpty()) {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Product List is empty")
                        }
                        showProductListIsEmpty = true
                        return@Button
                    }

                    if (billNo.isEmpty() || billNo.isBlank()) {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Bill no is not entered")
                        }
                        showBillNoError = true
                        return@Button
                    }
                    if (selectedClient == null) {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Client is not selected")
                        }
                        showClientError = true
                        return@Button
                    }

                    rootViewModel.submitFun()
                },
                enabled = !showProgressBar
            ) {
                Text(text = "Submit")
            }
        }
    ) {
        it.calculateTopPadding()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    scrollState
                )
        ) {


            Spacer(modifier = Modifier.height(4.dp))


            FirstThreeRows(
                rootViewModel = rootViewModel,
                hideKeyboard = hideKeyboard,
                onSelectDateClicked = {
                    showCalendar = true
                },
                navHostController = navHostController,
                onAddClientClicked = {
                    rootViewModel.setAddClientScreenNavPopUpFlag(true)
                    navHostController.navigate(route = RootNavScreens.AddClientScreen.route)
                },
                onBillNoError = {
                    showBillNoError = false
                },
                onClientError = {
                    showBillNoError = false
                },
                showBillNoError = showBillNoError,
                showClientError = showClientError
            )


            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(horizontal = 12.dp)
                    .border(
                        width = Dp.Hairline,
                        color = if (showProductListIsEmpty) MaterialTheme.colors.error else Color.LightGray
                    ),
                elevation = 0.dp
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    ProductListTitle()
                    Spacer(modifier = Modifier.height(8.dp))
                    ProductList(
                        rootViewModel = rootViewModel,
                        onProductListItemClicked = { i, productSelected ->
                            productSelectedForList = productSelected
                            countSelectedForList = i
                            showListEditAlertDialog = true
                        },
                        lazyColumnState = lazyColumState
                    )
                }
            }

            if (showProductListIsEmpty) {
                Text(
                    text = "  Product List is empty",
                    color = MaterialTheme.colors.error,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 10.dp)
                )
            }



            Spacer(modifier = Modifier.height(8.dp))


            ItemSelectionRows(
                rootViewModel = rootViewModel,
                navHostController = navHostController,
                hideKeyboard = hideKeyboard,
                onScanButtonClicked = onScanButtonClicked,
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
                    fManager.clearFocus()
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
                },
                onAdditionalDiscountAdded = {
                    rootViewModel.setShowAdditionalDiscount(true)
                },
                onFreightChargeAdded = {
                    rootViewModel.setShowFreightCharge(true)
                },
                onClearButtonClicked = {
                    fManager.clearFocus()
                }
            )


            if (showAdditionalDiscount) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Additional Discount:- ",
                        color = MaterialTheme.colors.primary,
                        fontStyle = MaterialTheme.typography.h6.fontStyle,
                        fontSize = MaterialTheme.typography.h6.fontSize,
                        modifier = Modifier.weight(3f)
                    )
                    OutlinedTextField(
                        value = additionalDiscount,
                        modifier = Modifier.weight(1f),
                        onValueChange = { value ->
                            rootViewModel.setAdditionalDiscount(value)
                        },
                        placeholder = {
                            Text(text = "0.0")
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Decimal
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                hideKeyboard()
                            }
                        )
                    )
                }
            }
            if (showFreightCharge) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Freight Charge:- ",
                        color = MaterialTheme.colors.primary,
                        fontStyle = MaterialTheme.typography.h6.fontStyle,
                        fontSize = MaterialTheme.typography.h6.fontSize,
                        modifier = Modifier.weight(3f)
                    )
                    OutlinedTextField(
                        value = freightCharge,
                        modifier = Modifier.weight(1f),
                        onValueChange = { value ->
                            rootViewModel.setFreightCharge(value)
                        },
                        placeholder = {
                            Text(text = "0.0")
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Decimal
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                hideKeyboard()
                            }
                        )
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Is Cash Purchase",
                    color = MaterialTheme.colors.primary,
                    fontStyle = MaterialTheme.typography.h6.fontStyle,
                    fontSize = if (booleanResource(id = R.bool.is_tablet)) 20.sp else 16.sp
                )
                Spacer(modifier = Modifier.width(12.dp))
                Checkbox(
                    checked = isCashPurchase,
                    onCheckedChange = { value ->
                        rootViewModel.setIsCashPurchase(value)
                    }
                )

            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .padding(horizontal = 10.dp)
                    .border(
                        width = 0.25.dp,
                        color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
                    )
            ) {}
            Spacer(modifier = Modifier.height(10.dp))


            ProductPriceColumn(
                rootViewModel = rootViewModel
            )


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