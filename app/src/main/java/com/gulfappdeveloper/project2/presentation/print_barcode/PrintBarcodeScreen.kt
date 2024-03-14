package com.gulfappdeveloper.project2.presentation.print_barcode

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.R
import com.gulfappdeveloper.project2.domain.models.barcode_print.BarcodeDesign
import com.gulfappdeveloper.project2.domain.models.remote.get.Product
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.print_barcode.components.PrintBarcodeSuccessDialog
import com.gulfappdeveloper.project2.presentation.print_barcode.util.PrintBarcodeScreenEvent
import com.gulfappdeveloper.project2.presentation.ui_util.ScanFrom
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import com.gulfappdeveloper.project2.presentation.ui_util.localDateToStringConverter
import com.gulfappdeveloper.project2.presentation.ui_util.screenSize
import com.gulfappdeveloper.project2.ui.theme.OrangeColor
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeker.sheets.core.views.HeaderComponent
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import kotlinx.coroutines.flow.collectLatest


private const val TAG = "PrintBarcodeScreen"

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrintBarcodeScreen(
    navHostController: NavHostController,
    hideKeyboard: () -> Unit,
    onScanButtonClicked: (ScanFrom) -> Unit,
    rootViewModel: RootViewModel,
    printBarcodeScreenViewModel: PrintBarcodeScreenViewModel = hiltViewModel()
) {


    val scrollState = rememberScrollState()

    val lazyColumState = rememberLazyListState()

    val screenWidth = screenSize().value

    var showProgressBar by remember {
        mutableStateOf(false)
    }

    val snackBarHostState = remember {
        SnackbarHostState()
    }

    var listOfDesigns: List<BarcodeDesign> by remember {
        mutableStateOf(emptyList())
    }

    var errorMessage: String? by remember {
        mutableStateOf(null)
    }


    val expiryDate by printBarcodeScreenViewModel.expiryDate

    val expiryDateState = rememberUseCaseState()

    val manufactureDate by printBarcodeScreenViewModel.manufactureDate

    val manufactureDateState = rememberUseCaseState()


    val selectedBarcodeDesign by printBarcodeScreenViewModel.selectedBarcodeDesign

    val startingPosition by printBarcodeScreenViewModel.startingPosition

    var showDropDownMenu by remember {
        mutableStateOf(false)
    }


    val selectedProductList = printBarcodeScreenViewModel.selectedProductList

    val product by printBarcodeScreenViewModel.product

    val productQty by printBarcodeScreenViewModel.productQty

    val barcode by printBarcodeScreenViewModel.enteredBarcode


    // Validation Error
    var showBarcodeDesignsSelectedBarcodesEmptyError by remember {
        mutableStateOf(false)
    }

    var showBarcodeDesignNotSelectedError by remember {
        mutableStateOf(false)
    }

    var showExpiryDateSelectedError by remember {
        mutableStateOf(false)
    }
    var showManufactureDateSelectedError by remember {
        mutableStateOf(false)
    }


    val showSubmitAlertDialog by printBarcodeScreenViewModel.showSubmittedAlertDialog


    var selectedProductEditOrDeleteDialog: Pair<Product, Int>? by remember {
        mutableStateOf(null)
    }

    var selectedProductCount: Int? by remember {
        mutableStateOf(null)
    }













    LaunchedEffect(true) {

        printBarcodeScreenViewModel.collectCameraScannedValue(rootViewModel = rootViewModel)


        printBarcodeScreenViewModel.printBarcodeScreenEvent.collectLatest { value: PrintBarcodeScreenEvent ->
            when (value.uiEvent) {
                is UiEvent.ShowProgressBar -> {
                    showProgressBar = true
                }

                is UiEvent.CloseProgressBar -> {
                    showProgressBar = false
                }

                is UiEvent.Navigate -> {

                }

                is UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(value.uiEvent.message)
                }

                is UiEvent.GetBarcodeDesigns -> {
                    listOfDesigns = value.uiEvent.listBarcodeDesigns
                }

                is UiEvent.ShowErrorValue -> {
                    errorMessage = value.uiEvent.errorMessage
                }

                is UiEvent.ShowBarcodeDesignNotSelectedError -> {
                    showBarcodeDesignNotSelectedError = true
                }

                is UiEvent.ShowBarcodeDesignManufactureDateNotSelectedError -> {
                    showManufactureDateSelectedError = true
                }

                is UiEvent.ShowBarcodeDesignExpiryDateNotSelectedError -> {
                    showExpiryDateSelectedError = true
                }

                is UiEvent.ShowBarcodeDesignSelectedBarcodeDesignsEmptyError -> {
                    showBarcodeDesignsSelectedBarcodesEmptyError = true
                }

                is UiEvent.ShowBarcodeDesignShowSelectedProductEditOrDeleteAlertDialog -> {
                    selectedProductEditOrDeleteDialog = value.uiEvent.pair
                    selectedProductCount = value.uiEvent.count
                }

                else -> Unit
            }
        }
    }




    CalendarDialog(
        state = expiryDateState,
        config = CalendarConfig(
            yearSelection = true,
            monthSelection = true,
            style = CalendarStyle.MONTH
        ),
        selection = CalendarSelection.Date(
            selectedDate = expiryDate
        ) { newDate ->
            printBarcodeScreenViewModel.setExpiryDate(newDate)
            expiryDateState.hide()
        }
    )

    CalendarDialog(
        state = manufactureDateState,
        config = CalendarConfig(
            yearSelection = true,
            monthSelection = true,
            style = CalendarStyle.MONTH
        ),
        selection = CalendarSelection.Date(
            selectedDate = manufactureDate
        ) { newDate ->
            printBarcodeScreenViewModel.setManuFactureDate(newDate)
            manufactureDateState.hide()
        }
    )





    if (showSubmitAlertDialog) {
        PrintBarcodeSuccessDialog {
            printBarcodeScreenViewModel.setShowSubmitAlertDialog(false)
            printBarcodeScreenViewModel.resetAllValues()
        }
    }


    if (selectedProductEditOrDeleteDialog != null && selectedProductCount != null) {
        BasicAlertDialog(onDismissRequest = {
            selectedProductEditOrDeleteDialog = null
            selectedProductCount = null
        }) {
            Card {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = selectedProductEditOrDeleteDialog!!.first.productName,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        textDecoration = TextDecoration.Underline
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Button(
                            onClick = {
                                printBarcodeScreenViewModel.deleteOneItemFromTheSelectedProductList(count = selectedProductCount!!)
                                selectedProductEditOrDeleteDialog = null
                                selectedProductCount = null
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.error,
                                contentColor = MaterialTheme.colorScheme.surface
                            )
                        ) {
                            Text(text = "Delete")
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(
                            onClick = {
                                printBarcodeScreenViewModel.editSelectedProduct(selectedProductCount!!)
                                selectedProductEditOrDeleteDialog = null
                                selectedProductCount = null
                            }
                        ) {
                            Text(text = "Edit")
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

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
                    Text(
                        text = "Print Barcode",
                        color = OrangeColor,
                        fontSize = if (screenWidth < 500f) 20.sp else if (screenWidth >= 500f && screenWidth < 900f) 22.sp else 28.sp,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        if (!showProgressBar) {
                            navHostController.popBackStack()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = OrangeColor
                        )
                    }
                },

                )
        },
        floatingActionButton = {
            if (showProgressBar) CircularProgressIndicator()
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        it.calculateTopPadding()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding(),
                    start = 16.dp,
                    end = 18.dp,
                )
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Selected Product
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        if (screenWidth < 600) 250.dp else if (screenWidth >= 600 && screenWidth < 800) 300.dp else 400.dp
                    )
                    .padding(horizontal = 0.dp)
                    .border(
                        width = Dp.Hairline,
                        color = if (showBarcodeDesignsSelectedBarcodesEmptyError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface,
                        shape = RoundedCornerShape(2)
                    ),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                shape = RoundedCornerShape(2)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Product Name",
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(6f)
                                .padding(start = 4.dp)
                        )
                        Text(
                            text = "Qty",
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "",
                            color = MaterialTheme.colorScheme.primary,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    LazyColumn(
                        state = lazyColumState
                    ) {
                        itemsIndexed(selectedProductList) { count, pair ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {

                                Text(
                                    text = pair.first.productName,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(6f)
                                        .padding(start = 4.dp),
                                    fontSize = 11.sp
                                )

                                Text(
                                    text = pair.second.toString(),
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f),
                                    textAlign = TextAlign.Center,
                                    fontSize = 11.sp
                                )

                                Icon(
                                    modifier = Modifier
                                        .size(18.dp)
                                        .clickable {
                                            printBarcodeScreenViewModel.onSelectedProductClickedForEdit(
                                                count,
                                                pair
                                            )

                                            Log.d(TAG, "PrintBarcodeScreen: $count")
                                            Log.e(TAG, "PrintBarcodeScreen: $pair")


                                        }
                                        .weight(1f),
                                    imageVector = Icons.Filled.ArrowDropDown,
                                    tint = OrangeColor,
                                    contentDescription = null,
                                )


                            }
                        }

                    }


                }
            }

            if (showBarcodeDesignsSelectedBarcodesEmptyError) {
                Text(
                    text = "Product List is empty",
                    modifier = Modifier.padding(start = 8.dp),
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = barcode,
                onValueChange = { b ->
                    printBarcodeScreenViewModel.setEnteredBarcode(b)
                },
                label = {
                    Text(
                        text = "Barcode",
                        color = if (barcode.isEmpty()) MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.5f
                        ) else MaterialTheme.colorScheme.primary
                    )
                },
                placeholder = {
                    Text(
                        text = "Enter Barcode",
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                },
                trailingIcon = {
                    IconButton(onClick = {

                        if (barcode.isEmpty()) {
                            onScanButtonClicked(ScanFrom.PRINT_BARCODE)
                        } else {
                            printBarcodeScreenViewModel.searchProductByQrCode(
                                barcode
                            )
                            hideKeyboard()
                        }
                    }) {
                        if (barcode.isEmpty()) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_barcode_scanner_24),
                                contentDescription = null,
                                tint = OrangeColor,
                            )
                        } else {
                            Icon(imageVector = Icons.Filled.Search, contentDescription = null)
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        printBarcodeScreenViewModel.searchProductByQrCode(barcode)
                        hideKeyboard()
                    }
                ),
                shape = MaterialTheme.shapes.small,
                textStyle = TextStyle(color = MaterialTheme.colorScheme.primary)
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = product?.productName ?: "",
                onValueChange = {},
                shape = MaterialTheme.shapes.medium,
                readOnly = true,
                textStyle = TextStyle(
                    color = if (product == null) Color.Black else MaterialTheme.colorScheme.primary
                ),
                label = {
                    Text(text = "Product Name")
                },
                enabled = false,

                colors = OutlinedTextFieldDefaults.colors(
                    disabledContainerColor = MaterialTheme.colorScheme.surface,
                    disabledBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    disabledLabelColor = if (product == null) MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.5f
                    ) else MaterialTheme.colorScheme.primary,
                    disabledTextColor = MaterialTheme.colorScheme.primary
                )

            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    value = productQty,
                    onValueChange = { qty ->
                        printBarcodeScreenViewModel.setProductQty(qty)
                    },
                    label = {
                        Text(text = "Qty")
                    },
                    placeholder = {
                        Text(
                            text = "Enter Qty",
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    shape = MaterialTheme.shapes.medium,
                    textStyle = TextStyle(color = MaterialTheme.colorScheme.primary)

                )


                Spacer(modifier = Modifier.width(4.dp))

                FloatingActionButton(
                    onClick = {
                        hideKeyboard()

                        printBarcodeScreenViewModel.addSelectedProductList(
                            product,
                            productQty.toInt()
                        )
                    },
                    shape = RoundedCornerShape(25.dp),
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.surface,
                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(
                        defaultElevation = 0.dp,
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.surface,
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 0.8.dp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(24.dp))





            Box(modifier = Modifier.fillMaxWidth()) {
                ExposedDropdownMenuBox(expanded = showDropDownMenu, onExpandedChange = {
                    showDropDownMenu = !showDropDownMenu
                }) {
                    OutlinedTextField(
                        value = selectedBarcodeDesign?.designName
                            ?: "Please select a Barcode Design",
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = showDropDownMenu)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        textStyle = TextStyle(
                            color = if (selectedBarcodeDesign == null) Color.Black.copy(alpha = 0.5f) else MaterialTheme.colorScheme.primary
                        ),
                        shape = MaterialTheme.shapes.medium,
                        isError = showBarcodeDesignNotSelectedError,
                        supportingText = {
                            if (showBarcodeDesignNotSelectedError) {
                                Text(
                                    text = "Barcode design not selected",
                                    color = MaterialTheme.colorScheme.error,
                                )
                            }
                        },
                        label = {
                            Text(text = "Barcode Design")
                        }
                    )
                    if (showDropDownMenu) {
                        showBarcodeDesignNotSelectedError = false
                    }

                    ExposedDropdownMenu(
                        modifier = Modifier.fillMaxWidth(),
                        expanded = showDropDownMenu,
                        onDismissRequest = { showDropDownMenu = false },
                    ) {
                        listOfDesigns.forEach { barcodeDesign ->
                            DropdownMenuItem(
                                modifier = Modifier.fillMaxWidth(),
                                text = { Text(text = barcodeDesign.designName) },
                                onClick = {
                                    printBarcodeScreenViewModel.setSelectedBarcodeDesign(
                                        barcodeDesign = barcodeDesign
                                    )
                                    showDropDownMenu = false
                                },
                            )
                        }

                    }
                }
            }


            Row(modifier = Modifier.fillMaxWidth()) {

                OutlinedTextField(
                    value = manufactureDate?.localDateToStringConverter() ?: "Select",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    label = {
                        Text(text = "Manufacture Date")
                    },
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = {
                            showManufactureDateSelectedError = false
                            manufactureDateState.show()
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.outline_calendar_month_24),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                            )
                        }
                    },
                    shape = MaterialTheme.shapes.medium,
                    textStyle = TextStyle(
                        color = if (manufactureDate == null) Color.Black.copy(alpha = 0.5f) else MaterialTheme.colorScheme.primary
                    ),
                    isError = showManufactureDateSelectedError,
                    supportingText = {
                        if (showManufactureDateSelectedError) {
                            Text(
                                text = "Manufacture date Not selected",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.width(4.dp))

                OutlinedTextField(
                    value = expiryDate?.localDateToStringConverter() ?: "Select",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    label = {
                        Text(text = "Expiry Date")
                    },
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = {
                            showExpiryDateSelectedError = false
                            expiryDateState.show()
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.outline_calendar_month_24),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                            )
                        }
                    },
                    shape = MaterialTheme.shapes.medium,
                    textStyle = TextStyle(
                        color = if (expiryDate == null) Color.Black.copy(alpha = 0.5f) else MaterialTheme.colorScheme.primary
                    ),
                    isError = showExpiryDateSelectedError,
                    supportingText = {
                        if (showExpiryDateSelectedError) {
                            Text(
                                text = "Expiry date Not selected",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )
            }


            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = startingPosition,
                onValueChange = { startingPosition ->
                    printBarcodeScreenViewModel.setStartingPosition(startingPosition)
                },
                shape = MaterialTheme.shapes.medium,
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.primary
                ),
                label = {
                    Text(text = "Starting Position")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                keyboardActions = KeyboardActions(onDone = {
                    hideKeyboard()
                }
                ),
                maxLines = 1,
                singleLine = true
            )

            Spacer(
                modifier = Modifier.height(32.dp),
            )

            Button(onClick = printBarcodeScreenViewModel::onPrintBarcode) {
                Text(text = "Print Barcode")
            }

            Spacer(modifier = Modifier.height(250.dp))


        }
    }
}