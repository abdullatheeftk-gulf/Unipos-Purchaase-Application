package com.gulfappdeveloper.project2.presentation.price_adjustment_screens.adjust_price_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.domain.models.remote.post.price_adjustment.PriceAdjustment
import com.gulfappdeveloper.project2.navigation.root.RootNavScreens
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.price_adjustment_screens.adjust_price_screen.componenets.ShowSuccessAlertDialog
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import com.gulfappdeveloper.project2.ui.theme.OrangeColor
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AdjustPriceScreen(
    rootViewModel: RootViewModel,
    navHostController: NavHostController,
    hideKeyboard: () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()

    val selectedProduct by rootViewModel.selectedProductForPriceAdjustment.value

    var showProgressBar by remember {
        mutableStateOf(false)
    }
    var showError by remember {
        mutableStateOf(false)
    }
    val purchasePrice by rootViewModel.purchasePriceForPriceAdjustment.value
    val salesPrice by rootViewModel.salePriceForPriceAdjustment.value

    val mrp by rootViewModel.mrpForPriceAdjustment.value

    var showSuccessAlertDialog by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        rootViewModel.adjustPriceScreenEvent.collectLatest { value ->
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
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }

                else -> Unit

            }
        }
    }

    if (showSuccessAlertDialog) {
        ShowSuccessAlertDialog {
            rootViewModel.resetAllPriceAdjustmentData()
           // navHostController.backQueue.clear()
            navHostController.navigate(
                route = RootNavScreens.MainScreen.route
            ){
                popUpTo(route = RootNavScreens.MainScreen.route){
                    inclusive = true
                }
            }
        }
    }



    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Price adjustment",
                        color = MaterialTheme.colors.OrangeColor
                    )
                },
                backgroundColor = MaterialTheme.colors.surface,
                navigationIcon = {
                    IconButton(onClick = {
                        navHostController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colors.OrangeColor
                        )
                    }
                },

                )
        }
    ) {
        it.calculateTopPadding()

        Box {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Product Name",
                        modifier = Modifier.weight(2.3f),
                        textAlign = TextAlign.End
                    )
                    Text(
                        text = " :  ",
                        modifier = Modifier.weight(.2f),
                        color = Color.Red
                    )
                    Text(
                        text = selectedProduct?.productName ?: "",
                        modifier = Modifier.weight(4f),
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.primary,
                        fontStyle = MaterialTheme.typography.h6.fontStyle,
                        fontSize = 18.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Barcode",
                        modifier = Modifier.weight(2.3f),
                        textAlign = TextAlign.End
                    )
                    Text(
                        text = " :  ",
                        modifier = Modifier.weight(0.2f),
                        color = Color.Red
                    )
                    Text(
                        text = selectedProduct?.barcode ?: "",
                        modifier = Modifier.weight(4f),
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.OrangeColor,
                        fontStyle = MaterialTheme.typography.h6.fontStyle,
                        fontSize = 18.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Stock",
                        modifier = Modifier.weight(2.3f),
                        textAlign = TextAlign.End
                    )
                    Text(
                        text = " :  ",
                        modifier = Modifier.weight(.2f),
                        color = Color.Red
                    )
                    Text(
                        text = selectedProduct?.stock.toString(),
                        modifier = Modifier.weight(4f),
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.primaryVariant,
                        fontStyle = MaterialTheme.typography.h6.fontStyle,
                        fontSize = 18.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = purchasePrice,
                    onValueChange = { value ->
                        showError = false
                        rootViewModel.setPurchasePriceForPriceAdjustment(value)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            hideKeyboard()
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Decimal
                    ),
                    textStyle = TextStyle(
                        color = MaterialTheme.colors.primary
                    ),
                    label = {
                        Text(text = "Purchase price")
                    }
                )

                Spacer(modifier = Modifier.height(4.dp))

                OutlinedTextField(
                    value = salesPrice,
                    onValueChange = { value ->
                        showError = false
                        rootViewModel.setSalePriceForPriceAdjustment(value)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            hideKeyboard()
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Decimal
                    ),
                    textStyle = TextStyle(
                        color = MaterialTheme.colors.primary
                    ),
                    placeholder = {
                        Text(text = "0.0")
                    },
                    label = {
                        Text(text = "Sale price")
                    }
                )

                Spacer(modifier = Modifier.height(4.dp))

                if (showError) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Data is not entered",
                        color = MaterialTheme.colors.error
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        val pPrice = purchasePrice.toFloat()
                        val sPrice = salesPrice.toFloat()
                        val mrpPrice = mrp.toFloat()
                        if (pPrice == 0f && sPrice == 0f && mrpPrice == 0f) {
                            showError = true
                            return@Button
                        }
                        val priceAdjustment = PriceAdjustment(
                            mrp = mrpPrice,
                            barcode = selectedProduct?.barcode ?: "",
                            productId = selectedProduct?.productId ?: 1,
                            purchasePrice = pPrice,
                            salePrice = sPrice,
                        )
                        rootViewModel.adjustPrice(
                            priceAdjustment = priceAdjustment
                        )

                    },
                    enabled = !showProgressBar
                ) {
                    Text(text = "Submit")
                }

            }
            if (showProgressBar) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }


    }


}