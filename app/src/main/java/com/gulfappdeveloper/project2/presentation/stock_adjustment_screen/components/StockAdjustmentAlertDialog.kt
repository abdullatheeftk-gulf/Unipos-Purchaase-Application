package com.gulfappdeveloper.project2.presentation.stock_adjustment_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.gulfappdeveloper.project2.domain.models.remote.get.stock_adjustment.ProductStock
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.ui.theme.OrangeColor

@Composable
fun StockAdjustmentAlertDialog(
    rootViewModel: RootViewModel,
    onDismissRequest: () -> Unit,
    hideKeyboard: () -> Unit,
    productForStockAdjustment: ProductStock,
    index: Int,
) {
    var stockAdjustedProduct by remember {
        mutableStateOf(productForStockAdjustment)
    }

    var actualStock by remember {
        mutableStateOf("")
    }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        buttons = {
            Column(
                modifier = Modifier.padding(all = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Stock Adjustment",
                    color = MaterialTheme.colors.primary,
                    fontStyle = MaterialTheme.typography.h6.fontStyle,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    textDecoration = TextDecoration.Underline
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row() {
                    Text(
                        text = "Name",
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier.weight(2f)
                    )
                    Text(
                        text = ":",
                        modifier = Modifier.weight(0.5f)
                    )
                    Text(
                        text = productForStockAdjustment.productName,
                        modifier = Modifier.weight(2f)
                    )

                }
                Spacer(modifier = Modifier.height(12.dp))
                Row() {
                    Text(
                        text = "System Stock",
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier.weight(2f)
                    )
                    Text(
                        text = ":",
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier.weight(0.5f)
                    )
                    Text(
                        text = productForStockAdjustment.currSystemStock.toString(),
                        color = MaterialTheme.colors.OrangeColor,
                        modifier = Modifier.weight(2f)
                    )

                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Actual Stock",
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier.weight(2f)
                    )
                    Text(
                        text = ":",
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier.weight(0.5f)
                    )
                    OutlinedTextField(
                        value = actualStock,
                        onValueChange = { value ->
                            actualStock = value
                        },
                        modifier = Modifier.weight(2f),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Decimal
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                val actualStockValue =
                                    if (actualStock.isNotEmpty() || actualStock.isNotBlank()) actualStock.toFloat() else 0f
                                if (actualStockValue == productForStockAdjustment.currSystemStock) {
                                    onDismissRequest()
                                    return@KeyboardActions
                                }
                                stockAdjustedProduct =
                                    productForStockAdjustment.copy(curActualStock = actualStockValue)
                                rootViewModel.addSelectedProductForStockAdjustment(
                                    stockAdjustedProduct = stockAdjustedProduct,
                                    index = index
                                )
                                onDismissRequest()
                                hideKeyboard()
                            }
                        ),
                        singleLine = true,
                        placeholder = {
                            Text(text = "0.0")
                        }
                    )

                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = {
                    val actualStockValue =
                        if (actualStock.isNotEmpty() || actualStock.isNotBlank()) actualStock.toFloat() else 0f
                    if (actualStockValue == productForStockAdjustment.currSystemStock) {
                        onDismissRequest()
                        return@Button
                    }
                    stockAdjustedProduct =
                        productForStockAdjustment.copy(curActualStock = actualStockValue)
                    rootViewModel.addSelectedProductForStockAdjustment(
                        stockAdjustedProduct = stockAdjustedProduct,
                        index = index
                    )
                    onDismissRequest()
                    hideKeyboard()
                }) {
                    Text("Submit")
                }
            }
        }
    )

}