package com.gulfappdeveloper.project2.presentation.add_product_main_screen.presentation.add_product_home_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun PriceBlock(
    purchasePrice: String,
    sellingPrice: String,
    mrp: String,
    purchaseDisc: String,
    salesDisc: String,
    onPurchasePriceChange: (String) -> Unit,
    onSellingPriceChanged: (String) -> Unit,
    onMrpChange: (String) -> Unit,
    onPurchaseDiscChange: (String) -> Unit,
    onSalesDiscChange: (String) -> Unit,
    showPurchasePriceError: Boolean,
    showSellingPriceError:Boolean,
    hideKeyboard: () -> Unit
) {
    Spacer(modifier = Modifier.height(25.dp))
    Text(
        text = "Price Details",
        color = MaterialTheme.colors.primary,
        fontSize = MaterialTheme.typography.h6.fontSize,
        fontStyle = MaterialTheme.typography.h6.fontStyle,
        textDecoration = TextDecoration.Underline
    )
    Spacer(modifier = Modifier.height(8.dp))
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(end = 4.dp)
        ) {
            OutlinedTextField(
                value = purchasePrice,
                onValueChange = onPurchasePriceChange,
                label = {
                    Row() {
                        Text(text = "Purchase Price")
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = MaterialTheme.colors.error,
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
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Decimal
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        hideKeyboard()
                    }
                ),
                singleLine = true,
                maxLines = 1,
                placeholder = {
                    Text(text = "0.0")
                },
                isError = showPurchasePriceError,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colors.primary,
                    backgroundColor = MaterialTheme.colors.surface
                )

            )
        }

        OutlinedTextField(
            value = sellingPrice,
            onValueChange = onSellingPriceChanged,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp),
            label = {
                Row() {
                    Text(text = "Selling Price")
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.error,
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
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Decimal
            ),
            singleLine = true,
            maxLines = 1,
            placeholder = {
                Text(text = "0.0")
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.primary,
                backgroundColor = MaterialTheme.colors.surface
            ),
            isError = showSellingPriceError
        )
        OutlinedTextField(
            value = mrp,
            onValueChange = onMrpChange,
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp),
            label = {
                // Row() {
                Text(text = "MRP")
                /* Text(
                     buildAnnotatedString {
                         withStyle(
                             style = SpanStyle(
                                 color = MaterialTheme.colors.error,
                                 baselineShift = BaselineShift.Superscript
                             )
                         ) {
                             append("*")
                         }
                     },
                 )*/
                //}
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Decimal
            ),
            singleLine = true,
            maxLines = 1,
            placeholder = {
                Text(text = "0.0")
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.primary,
                backgroundColor = MaterialTheme.colors.surface
            )
        )
    }

    Row(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = purchaseDisc,
            onValueChange = onPurchaseDiscChange,
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp),
            label = {
                Text(text = "Purchase Discount")
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Decimal
            ),
            singleLine = true,
            maxLines = 1,
            placeholder = {
                Text(text = "0.0")
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.primary,
                backgroundColor = MaterialTheme.colors.surface
            )
        )
        OutlinedTextField(
            value = salesDisc,
            onValueChange = onSalesDiscChange,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp),
            label = {
                Text(text = "Sales Disc")
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Decimal
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    hideKeyboard()
                }
            ),
            singleLine = true,
            maxLines = 1,
            placeholder = {
                Text(text = "0.0")
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.primary,
                backgroundColor = MaterialTheme.colors.surface
            )
        )
    }

}