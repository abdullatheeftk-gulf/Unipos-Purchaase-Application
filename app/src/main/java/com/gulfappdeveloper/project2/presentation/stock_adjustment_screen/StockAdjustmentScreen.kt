package com.gulfappdeveloper.project2.presentation.stock_adjustment_screen

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.stock_adjustment_screen.components.EmptyList
import com.gulfappdeveloper.project2.presentation.stock_adjustment_screen.components.StockAdjustSubmittedAlertDialog
import com.gulfappdeveloper.project2.presentation.stock_adjustment_screen.components.StockAdjustmentAlertDialog
import com.gulfappdeveloper.project2.presentation.stock_adjustment_screen.components.TopBar
import com.gulfappdeveloper.project2.presentation.ui_util.ScanFrom
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import com.gulfappdeveloper.project2.ui.theme.OrangeColor
import kotlinx.coroutines.flow.collectLatest

private const val TAG = "StockAdjustmentScreen"

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StockAdjustmentScreen(
    rootViewModel: RootViewModel,
    navHostController: NavHostController,
    hideKeyboard: () -> Unit,
    onScanButtonClicked: (ScanFrom) -> Unit,
) {
    val scaffoldState = rememberScaffoldState()

    var showProgressbar by remember {
        mutableStateOf(false)
    }

    val productForStockAdjustment by rootViewModel.productStockForStockAdjustment.value

    var indexSelectedForStockAdjustment by remember {
        mutableStateOf(-1)
    }

    var showStockAdjustmentAlertDialog by remember {
        mutableStateOf(false)
    }

    var showStockAdjustSubmittedAlertDialog by remember {
        mutableStateOf(false)
    }


    val productList = rootViewModel.productListForStockAdjustment.value

    val stockAdjustedProductList = rootViewModel.stockAdjustedProductList.value

    LaunchedEffect(key1 = true) {
        rootViewModel.stockAdjustmentScreenEvent.collectLatest { value ->
            when (val event = value.uiEvent) {
                is UiEvent.ShowProgressBar -> {
                    showProgressbar = true
                }
                is UiEvent.CloseProgressBar -> {
                    showProgressbar = false
                }
                is UiEvent.ShowAlertDialog -> {
                    if (event.message == "stock_adjusted") {
                        showStockAdjustmentAlertDialog = true
                    } else {
                        showStockAdjustSubmittedAlertDialog = true
                    }
                }
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
                is UiEvent.Navigate -> {
                    navHostController.popBackStack()
                }
                else -> Unit
            }
        }
    }

    if (showStockAdjustmentAlertDialog) {
        productForStockAdjustment?.let {
            StockAdjustmentAlertDialog(
                rootViewModel = rootViewModel,
                onDismissRequest = {
                    showStockAdjustmentAlertDialog = false
                    indexSelectedForStockAdjustment = -1
                },
                hideKeyboard = {
                    hideKeyboard()
                    Log.e(TAG, "StockAdjustmentScreen: ")
                },
                productForStockAdjustment = it,
                index = indexSelectedForStockAdjustment
            )
        }
    }

    if (showStockAdjustSubmittedAlertDialog) {
        StockAdjustSubmittedAlertDialog {
            rootViewModel.resetAllStockAdjustmentData()
            showStockAdjustSubmittedAlertDialog = false
            navHostController.popBackStack()
        }
    }

    BackHandler(true) {
        rootViewModel.resetAllStockAdjustmentData()
        navHostController.popBackStack()
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                rootViewModel = rootViewModel,
                onScanButtonClicked = onScanButtonClicked,
                onBackButtonClicked = {
                    rootViewModel.resetAllStockAdjustmentData()
                    navHostController.popBackStack()
                },
                hideKeyboard = hideKeyboard,
                onClearButtonClicked = {
                    rootViewModel.setProductNameForStockAdjustment("")
                }
            )
        },
        floatingActionButton = {
            if (stockAdjustedProductList.isNotEmpty()) {
                ExtendedFloatingActionButton(
                    text = {

                        BadgedBox(badge = {
                            Row() {
                                Text(
                                    text = "  " + stockAdjustedProductList.size.toString(),
                                    color = MaterialTheme.colors.OrangeColor,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                            }

                        }) {
                            Text(
                                text = "Submit",
                                color = if (showProgressbar) Color.White else MaterialTheme.colors.surface,
                                fontSize = MaterialTheme.typography.h6.fontSize,
                                fontStyle = MaterialTheme.typography.h6.fontStyle,
                                modifier = Modifier.alpha(
                                    if (showProgressbar) ContentAlpha.disabled else ContentAlpha.high
                                )
                            )
                        }

                    },
                    onClick = {
                        if (!showProgressbar) {
                            rootViewModel.submitStockAdjustment()
                        }
                    },
                    backgroundColor = if (showProgressbar) MaterialTheme.colors.onSurface else MaterialTheme.colors.primary,
                    shape = RoundedCornerShape(25),
                    modifier = Modifier.alpha(
                        if (showProgressbar) ContentAlpha.disabled else ContentAlpha.high
                    )
                )

            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        it.calculateTopPadding()

        if (productList.isEmpty()) {
            if (!showProgressbar) {
                EmptyList()
            }
        } else {
            LazyColumn {
                item { 
                    Spacer(modifier = Modifier.height(4.dp))
                }
                itemsIndexed(productList) { index, pair ->
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        border = BorderStroke(
                            width = 1.dp,
                            color = if (pair.first) MaterialTheme.colors.OrangeColor else MaterialTheme.colors.primary
                        ),
                        color = if (pair.first) MaterialTheme.colors.OrangeColor else MaterialTheme.colors.background,
                        shape = RoundedCornerShape(35),
                        onClick = {
                            rootViewModel.getStockOfAProduct(barcode = pair.second.barcode)
                            indexSelectedForStockAdjustment = index
                        },
                    ) {
                        Text(
                            text = pair.second.productName,
                            modifier = Modifier.padding(all = 10.dp),
                            color = if (pair.first) MaterialTheme.colors.surface else MaterialTheme.colors.onSurface
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(200.dp))
                }
            }
        }
        if (showProgressbar) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }


    }
}