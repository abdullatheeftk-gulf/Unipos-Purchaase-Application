package com.gulfappdeveloper.project2.presentation.stock_adjustment_screen


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.stock_adjustment_screen.components.EmptyList
import com.gulfappdeveloper.project2.presentation.stock_adjustment_screen.components.StockAdjustSubmittedAlertDialog
import com.gulfappdeveloper.project2.presentation.stock_adjustment_screen.components.StockAdjustmentAlertDialog
import com.gulfappdeveloper.project2.presentation.stock_adjustment_screen.components.TopBar
import com.gulfappdeveloper.project2.presentation.ui_util.ScanFrom
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import com.gulfappdeveloper.project2.presentation.ui_util.screenSize
import com.gulfappdeveloper.project2.ui.theme.OrangeColor
import kotlinx.coroutines.flow.collectLatest


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockAdjustmentScreen(
    rootViewModel: RootViewModel,
    navHostController: NavHostController,
    hideKeyboard: () -> Unit,
    onScanButtonClicked: (ScanFrom) -> Unit,
) {
    val screenWidth = screenSize().value

    val snackBarHostState = remember {
        SnackbarHostState()
    }

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
                    snackBarHostState.showSnackbar(event.message)
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
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
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
                    content = {

                        BadgedBox(badge = {
                            Row() {
                                Text(
                                    text = " " + stockAdjustedProductList.size.toString(),
                                    color = OrangeColor,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                            }

                        }) {
                            Text(
                                text = "Submit",
                                color = if (showProgressbar) Color.White else MaterialTheme.colorScheme.surface,
                                fontSize = if(screenWidth<600f) 18.sp else if(screenWidth>=600 && screenWidth<800) 22.sp else 26.sp,
                                fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
                                modifier = Modifier.alpha(
                                    if (showProgressbar) 0.3f else 1f
                                )
                            )
                        }

                    },
                    onClick = {
                        if (!showProgressbar) {
                            rootViewModel.submitStockAdjustment()
                        }
                    },
                    containerColor = if (showProgressbar) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(20),
                    modifier = Modifier.alpha(
                        if (showProgressbar) 0.3f else 1f
                    )
                )

            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {paddingValues->


        if (productList.isEmpty()) {
            if (!showProgressbar) {
                EmptyList()
            }
        } else {
            LazyColumn(contentPadding = paddingValues) {
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
                            color = if (pair.first) OrangeColor else MaterialTheme.colorScheme.primary
                        ),
                        color = if (pair.first) OrangeColor else MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(35),
                        onClick = {
                            rootViewModel.getStockOfAProduct(barcode = pair.second.barcode)
                            indexSelectedForStockAdjustment = index
                        },
                    ) {
                        Text(
                            text = pair.second.productName,
                            modifier = Modifier.padding(all = 10.dp),
                            color = if (pair.first) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.onSurface,
                            fontSize = if(screenWidth<600f) 14.sp else if(screenWidth>=600 && screenWidth<800) 18.sp else 22.sp,
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