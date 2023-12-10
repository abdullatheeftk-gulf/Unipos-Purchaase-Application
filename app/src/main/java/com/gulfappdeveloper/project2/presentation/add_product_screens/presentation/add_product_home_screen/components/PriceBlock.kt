package com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.add_product_home_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.presentation.ui_util.screenSize

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
    showSellingPriceError: Boolean,
    hideKeyboard: () -> Unit
) {

    val screenWidth = screenSize().value

    val focusRequester by remember {
        mutableStateOf(FocusRequester())
    }
    var focusOnPriceOutlinedTextField by remember {
        mutableStateOf(false)
    }

    Spacer(modifier = Modifier.height(25.dp))
    Text(
        text = "Price Details",
        color = MaterialTheme.colorScheme.primary,
        fontSize = if (screenWidth < 600) 20.sp else if (screenWidth >= 600 && screenWidth < 800) 24.sp else 28.sp,
        fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
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
                    Text(
                        text = if (purchasePrice.isNotEmpty() || focusOnPriceOutlinedTextField) "Purch price" else "Purchase price",
                        fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                    )
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
                    Text(
                        text = "0.0",
                        fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                    )
                },
                isError = showPurchasePriceError,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = MaterialTheme.colorScheme.primary,
                ),
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        focusOnPriceOutlinedTextField = it.isFocused
                    },
                textStyle = TextStyle(
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
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
                    Text(
                        text = "Selling Price",
                        fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                    )
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
                        fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Decimal
            ),
            singleLine = true,
            maxLines = 1,
            placeholder = {
                Text(
                    text = "0.0",
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = MaterialTheme.colorScheme.primary,
            ),
            isError = showSellingPriceError,
            keyboardActions = KeyboardActions(
                onDone = {
                    hideKeyboard()
                }
            ),
            textStyle = TextStyle(
                fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
            )
        )
        OutlinedTextField(
            value = mrp,
            onValueChange = onMrpChange,
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp),
            label = {
                Text(
                    text = "MRP",
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Decimal
            ),
            singleLine = true,
            maxLines = 1,
            placeholder = {
                Text(
                    text = "0.0",
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = MaterialTheme.colorScheme.primary,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    hideKeyboard()
                }
            ),
            textStyle = TextStyle(
                fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
            )
        )
    }
    Spacer(modifier = Modifier.height(
       if(screenWidth<600) 2.dp else if(screenWidth>=600 && screenWidth<800) 4.dp else 8.dp
    ))
    Row(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = purchaseDisc,
            onValueChange = onPurchaseDiscChange,
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp),
            label = {
                Text(
                    text = "Purchase Discount",
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Decimal
            ),
            singleLine = true,
            maxLines = 1,
            placeholder = {
                Text(
                    text = "0.0",
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = MaterialTheme.colorScheme.primary,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    hideKeyboard()
                }
            ),
            textStyle = TextStyle(
                fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
            )
        )
        OutlinedTextField(
            value = salesDisc,
            onValueChange = onSalesDiscChange,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp),
            label = {
                Text(
                    text = "Sales Disc",
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                )
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
                Text(
                    text = "0.0",
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = MaterialTheme.colorScheme.primary,
            ),
            textStyle = TextStyle(
                fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
            )
        )
    }

}