package com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.add_product_home_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.R
import com.gulfappdeveloper.project2.domain.models.remote.get.for_add_product.ProductGroup
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.add_product_screens.AddProductMainViewModel
import com.gulfappdeveloper.project2.presentation.add_product_screens.navigation.AddProductScreens
import com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.add_product_home_screen.components.CheckBoxBlock
import com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.add_product_home_screen.components.OpeningStockBlock
import com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.add_product_home_screen.components.PriceBlock
import com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.add_product_home_screen.components.ShowMultiUnits
import com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.add_product_home_screen.components.SuccessAlertDialog
import com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.add_product_home_screen.components.TaxCategoryBlock
import com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.add_product_home_screen.components.UnitBlock
import com.gulfappdeveloper.project2.presentation.ui_util.ScanFrom
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import com.gulfappdeveloper.project2.ui.theme.OrangeColor
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductHomeScreen(
    rootViewModel: RootViewModel,
    navHostController: NavHostController,
    addProductNavHostController: NavHostController,
    hideKeyboard: () -> Unit,
    addProductMainViewModel: AddProductMainViewModel,
    onScanButtonClicked: (ScanFrom) -> Unit
) {

    val focusManager = LocalFocusManager.current

    val snackBarHostState = remember {
        SnackbarHostState()
    }

    val scope = rememberCoroutineScope()

    val scrollState = rememberScrollState()


    val productName by addProductMainViewModel.productName

    val localName by addProductMainViewModel.localName

    val productGroup: ProductGroup? by addProductMainViewModel.selectedProductGroup

    val specification by addProductMainViewModel.specification

    val barcode by addProductMainViewModel.barcode

    val openingStock by addProductMainViewModel.openingStock

    val purchasePrice by addProductMainViewModel.purchasePrice

    val sellingPrice by addProductMainViewModel.sellingPrice

    val mrp by addProductMainViewModel.mrp

    val purchaseDisc by addProductMainViewModel.purchaseDisc

    val salesDisc by addProductMainViewModel.salesDisc

    val productUnit by addProductMainViewModel.productUnit

    val taxCategory by addProductMainViewModel.taxCategory


    val isInclusive by addProductMainViewModel.isInclusive

    val isScale by addProductMainViewModel.isScale

    val multiUnitList = addProductMainViewModel.multiUnitProductList


    var showProductNameError by remember {
        mutableStateOf(false)
    }

    var showProductGroupError by remember {
        mutableStateOf(false)
    }

    var showBarcodeError by remember {
        mutableStateOf(false)
    }

    var showPurchasePriceError by remember {
        mutableStateOf(false)
    }

    var showSellingPriceError by remember {
        mutableStateOf(false)
    }

    var showTaxCategoryError by remember {
        mutableStateOf(false)
    }

    var showUnitError by remember {
        mutableStateOf(false)
    }

    var showProgressBar by remember {
        mutableStateOf(false)
    }

    var showSuccessAlertDialog by remember {
        mutableStateOf(false)
    }



    LaunchedEffect(key1 = true) {
        addProductMainViewModel.addProductEvent.collectLatest { value ->
            when (val event = value.uiEvent) {
                is UiEvent.ShowProgressBar -> {
                    showProgressBar = true
                }
                is UiEvent.CloseProgressBar -> {
                    showProgressBar = false
                }
                is UiEvent.ShowAlertDialog -> {
                    showSuccessAlertDialog = true
                }
                is UiEvent.AddedProduct -> {
                    rootViewModel.setProductSearchMode(false)
                    rootViewModel.setSelectedProduct(event.product)
                    rootViewModel.setQty("1")
                }
                is UiEvent.Navigate -> {
                    navHostController.popBackStack()
                }
                is UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(event.message)
                }
                else -> Unit
            }
        }
    }

    if (showSuccessAlertDialog) {
        SuccessAlertDialog {
            showSuccessAlertDialog = false
            scope.launch {
                scrollState.scrollTo(0)
            }

        }
    }

    BackHandler(true) {
        if (!showProgressBar) {
            navHostController.popBackStack()
        }
    }


    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        topBar = {
            TopAppBar(
                modifier = Modifier.shadow(elevation = 6.dp),
                title = {
                    Text(text = "Add Product", color = OrangeColor)
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if (!showProgressBar) {
                                navHostController.popBackStack()
                            }
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
                    TextButton(onClick = {
                        var errors = false
                        if (productName.isEmpty()) {
                            showProductNameError = true
                            errors = true
                        }
                        if (barcode.isEmpty()) {
                            showBarcodeError = true
                            errors = true
                        }

                        if (sellingPrice.isEmpty()) {
                            showSellingPriceError = true
                            errors = true
                        }
                        if (productUnit == null) {
                            showUnitError = true
                            errors = true
                        }
                        if (errors) {
                            scope.launch {
                                snackBarHostState.showSnackbar("Add required columns to continue")
                            }
                            return@TextButton
                        }
                        addProductNavHostController.navigate(AddProductScreens.MultiUnitScreen.route)
                    }) {
                        /*Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = null
                        )*/
                        Text("Multi Unit")
                        Spacer(modifier = Modifier.width(2.dp))
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface
                        )


                    }
                }
            )
        }
    ) {paddingValues->

        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 0.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(paddingValues.calculateTopPadding()))
            // Product name

            OutlinedTextField(
                value = productName,
                onValueChange = { value ->
                    showProductNameError = false
                    addProductMainViewModel.setProductName(value)
                },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2,
                label = {
                    Row {
                        Text(text = "Product Name")
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = MaterialTheme.colorScheme.error,
                                        baselineShift = BaselineShift.Superscript
                                    )
                                ) {
                                    append("*")
                                }
                            },
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),

                isError = showProductNameError,
                keyboardActions = KeyboardActions(
                    onDone = {
                        hideKeyboard()
                    }
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = MaterialTheme.colorScheme.primary,
                ),

            )

            if (showProductNameError) {
                Text(
                    text = "   Product name is required",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            // Local name
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = localName,
                onValueChange = { value ->
                    addProductMainViewModel.setLocalName(value)
                },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2,
                label = {
                    Text(text = "Local Name")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),

                keyboardActions = KeyboardActions(
                    onDone = {
                        hideKeyboard()
                    }
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = MaterialTheme.colorScheme.primary,
                ),
            )

            // Product group
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = productGroup?.pGroupName ?: "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        showProductGroupError = false
                        addProductMainViewModel.getProductGroups()
                        addProductNavHostController.navigate(route = AddProductScreens.SelectProductGroupScreen.route)
                    },
                maxLines = 1,
                label = {
                    Row {
                        Text(text = "Product Group")
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = MaterialTheme.colorScheme.error,
                                        baselineShift = BaselineShift.Superscript
                                    )
                                ) {
                                    append("*")
                                }
                            },
                        )
                    }
                },
                enabled = false,

                singleLine = true,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            showProductGroupError = false
                            addProductMainViewModel.getProductGroups()
                            addProductNavHostController.navigate(route = AddProductScreens.SelectProductGroupScreen.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowRight,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                },
                isError = showProductGroupError,
                colors = OutlinedTextFieldDefaults.colors(
                    disabledBorderColor = MaterialTheme.colorScheme.onSurface,
                    disabledTextColor = MaterialTheme.colorScheme.primary,
                    disabledLabelColor = MaterialTheme.colorScheme.onSurface
                )
            )

            if (showProductGroupError) {
                Text(
                    text = "    Product group is not selected",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            //Product Specification
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = specification,
                onValueChange = { value ->
                    addProductMainViewModel.setSpecification(value)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                label = {
                    Text(text = "Product Specification")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),

                keyboardActions = KeyboardActions(
                    onDone = {
                        hideKeyboard()
                    }
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = MaterialTheme.colorScheme.primary,
                ),
            )

            // Barcode
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Enter Barcode :- ",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1f)
                )

                Column(modifier = Modifier.weight(1.5f)) {
                    OutlinedTextField(
                        value = barcode,
                        onValueChange = { value ->
                            showBarcodeError = false
                            addProductMainViewModel.setBarcode(value)
                        },
                        label = {
                            Row {
                                Text(text = "Barcode")
                                Text(
                                    buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                color = MaterialTheme.colorScheme.error,
                                                baselineShift = BaselineShift.Superscript
                                            )
                                        ) {
                                            append("*")
                                        }
                                    },
                                )
                            }
                        },
                        isError = showBarcodeError,

                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            capitalization = KeyboardCapitalization.Characters
                        ),
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    onScanButtonClicked(ScanFrom.ADD_PRODUCT_SCREEN)
                                }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_baseline_barcode_scanner_24),
                                    contentDescription = null,
                                    tint = OrangeColor
                                )
                            }
                        },
                        keyboardActions = KeyboardActions(
                            onDone = {
                                hideKeyboard()
                            }
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedTextColor = MaterialTheme.colorScheme.primary,
                        ),
                    )
                    if (showBarcodeError) {
                        Text(
                            text = "    Barcode is required",
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.align(Alignment.Start)
                        )
                    }
                }

            }

            OpeningStockBlock(
                openingStock = openingStock,
                setOpeningStock = { value ->
                    addProductMainViewModel.setOpeningStock(value)
                },
                hideKeyboard = hideKeyboard,
            )

            // Price Details
            PriceBlock(
                purchasePrice = purchasePrice,
                sellingPrice = sellingPrice,
                mrp = mrp,
                purchaseDisc = purchaseDisc,
                salesDisc = salesDisc,
                onPurchasePriceChange = { value ->
                    showPurchasePriceError = false
                    addProductMainViewModel.setPurchasePrice(value)
                },
                onSellingPriceChanged = { value ->
                    addProductMainViewModel.setSellingPrice(value)
                },
                onMrpChange = { value ->
                    addProductMainViewModel.setMrp(value)
                },
                onPurchaseDiscChange = { value ->
                    addProductMainViewModel.setPurchaseDisc(value)
                },
                onSalesDiscChange = { value ->
                    addProductMainViewModel.setSalesDisc(value)
                },
                showPurchasePriceError = showPurchasePriceError,
                showSellingPriceError = showSellingPriceError,
                hideKeyboard = hideKeyboard
            )


            // Tax Category
            TaxCategoryBlock(
                selectedTaxCategory = taxCategory,
                onSelectedTaxCategory = { tax ->
                    showTaxCategoryError = false
                    addProductMainViewModel.setTaxCategory(tax)
                },
                addProductMainViewModel = addProductMainViewModel,
                showTaxCategoryError = showTaxCategoryError
            )

            UnitBlock(
                selectedUnits = productUnit,
                onUnitsSelected = { unit ->
                    showUnitError = false
                    addProductMainViewModel.setProductUnit(unit)
                },
                addProductMainViewModel = addProductMainViewModel,
                showUnitError = showUnitError
            )

            CheckBoxBlock(
                isInclusive = isInclusive,
                isScale = isScale,
                onSelectedInclusive = { value ->
                    addProductMainViewModel.setIsInclusive(value)
                },
                onSelectedScale = { value ->
                    addProductMainViewModel.setIsScale(value)
                }
            )



            if (multiUnitList.isNotEmpty()) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Multi Units",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
                    textDecoration = TextDecoration.Underline
                )
                Spacer(modifier = Modifier.height(4.dp))
                ShowMultiUnits(
                    multiUnitList = multiUnitList,
                    addProductMainViewModel = addProductMainViewModel
                )
            }


            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    var errors = false
                    if (productName.isEmpty()) {
                        showProductNameError = true
                        errors = true
                    }
                    if (productGroup == null) {
                        showProductGroupError = true
                        errors = true
                    }
                    if (barcode.isEmpty()) {
                        showBarcodeError = true
                        errors = true
                    }
                    if (sellingPrice.isEmpty()) {
                        showSellingPriceError = true
                        errors = true
                    }
                    if (taxCategory == null) {
                        showTaxCategoryError = true
                        errors = true
                    }
                    if (productUnit == null) {
                        showUnitError = true
                        errors = true
                    }
                    if (errors) {
                        scope.launch {
                            snackBarHostState.showSnackbar("Add required columns to continue")
                        }
                        return@Button
                    }
                    addProductMainViewModel.addProduct()
                    focusManager.clearFocus()
                },
                enabled = !showProgressBar
            ) {
                Text(text = "Add Product")
            }
            Spacer(modifier = Modifier.height(200.dp))

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



