package com.gulfappdeveloper.project2.presentation.stock_adjustment_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.domain.models.remote.get.stock_adjustment.ProductStock
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.ui_util.screenSize
import com.gulfappdeveloper.project2.ui.theme.OrangeColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockAdjustmentAlertDialog(
    rootViewModel: RootViewModel,
    onDismissRequest: () -> Unit,
    hideKeyboard: () -> Unit,
    productForStockAdjustment: ProductStock,
    index: Int,
) {
    val screenWidth = screenSize().value

    var stockAdjustedProduct by remember {
        mutableStateOf(productForStockAdjustment)
    }

    var actualStock by remember {
        mutableStateOf("")
    }

    AlertDialog(
        onDismissRequest = onDismissRequest,
    )
    {
        Card(elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)) {
            Column(
                modifier = Modifier.padding(all = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Stock Adjustment",
                    color = OrangeColor,
                    fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    textDecoration = TextDecoration.Underline
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row() {
                    Text(
                        text = "Name",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(2f),
                        fontSize = if (screenWidth < 600f) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                    )
                    Text(
                        text = ":",
                        modifier = Modifier.weight(0.5f),
                        fontSize = if (screenWidth < 600f) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                    )
                    Text(
                        text = productForStockAdjustment.productName,
                        modifier = Modifier.weight(2f),
                        fontSize = if (screenWidth < 600f) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                    )

                }
                Spacer(modifier = Modifier.height(12.dp))
                Row() {
                    Text(
                        text = "System Stock",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(2f),
                        fontSize = if (screenWidth < 600f) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                    )
                    Text(
                        text = ":",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(0.5f),
                        fontSize = if (screenWidth < 600f) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                    )
                    Text(
                        text = productForStockAdjustment.currSystemStock.toString(),
                        color = OrangeColor,
                        modifier = Modifier.weight(2f),
                        fontSize = if (screenWidth < 600f) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                    )

                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Actual Stock",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(2f),
                        fontSize = if (screenWidth < 600f) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                    )
                    Text(
                        text = ":",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(0.5f),
                        fontSize = if (screenWidth < 600f) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
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
                            Text(
                                text = "0.0",
                                fontSize = if (screenWidth < 600f) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                            )
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

    }

}