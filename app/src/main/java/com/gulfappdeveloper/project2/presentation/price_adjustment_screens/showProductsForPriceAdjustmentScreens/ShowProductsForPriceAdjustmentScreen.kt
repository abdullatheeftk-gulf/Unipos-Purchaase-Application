package com.gulfappdeveloper.project2.presentation.price_adjustment_screens.showProductsForPriceAdjustmentScreens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.navigation.root.RootNavScreens
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.price_adjustment_screens.showProductsForPriceAdjustmentScreens.components.PriceAdjustmentTopBar
import com.gulfappdeveloper.project2.presentation.stock_adjustment_screen.components.EmptyList
import com.gulfappdeveloper.project2.presentation.ui_util.ScanFrom
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShowProductsForPriceAdjustmentScreen(
    rootViewModel: RootViewModel,
    navHostController: NavHostController,
    onScanButtonClicked: (ScanFrom) -> Unit,
    hideKeyboard: () -> Unit
) {

    val scaffoldState = rememberScaffoldState()

    var showProgressbar by remember {
        mutableStateOf(false)
    }




    val productList = rootViewModel.productListForPriceAdjustment.value


    LaunchedEffect(key1 = true) {
        rootViewModel.showProductsForPriceAdjustmentScreenEvent.collectLatest { value ->
            when (val event = value.uiEvent) {
                is UiEvent.ShowProgressBar -> {
                    showProgressbar = true
                }
                is UiEvent.CloseProgressBar -> {
                    showProgressbar = false
                }

                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
                else -> Unit
            }
        }
    }


    BackHandler(true) {
        rootViewModel.resetAllPriceAdjustmentData()
        navHostController.popBackStack()
    }


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            PriceAdjustmentTopBar(
                rootViewModel = rootViewModel,
                onScanButtonClicked = onScanButtonClicked,
                onBackButtonClicked = {
                    rootViewModel.resetAllPriceAdjustmentData()
                    navHostController.popBackStack()
                },
                hideKeyboard = hideKeyboard,
                onClearButtonClicked = {
                    rootViewModel.setProductNameSearchForPriceAdjustment("")
                }
            )
        }
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
                items(productList) { product ->
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        border = BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colors.primary
                        ),
                        color = MaterialTheme.colors.background,
                        shape = RoundedCornerShape(35),
                        onClick = {
                            rootViewModel.getProductForPriceAdjustment(product.barcode)
                            navHostController.navigate(route = RootNavScreens.AdjustPriceScreen.route)
                        },
                    ) {
                        Text(
                            text = product.productName,
                            modifier = Modifier.padding(all = 10.dp),
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

