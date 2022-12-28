package com.gulfappdeveloper.project2.presentation.home_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.R
import com.gulfappdeveloper.project2.navigation.root.RootNavScreens
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.navigation.root.RootViewModel2
import com.gulfappdeveloper.project2.presentation.home_screen.util.ProductUnit

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemSelectionRows2(
    rootViewModel: RootViewModel,
    navHostController: NavHostController,
    hideKeyboard: () -> Unit,
    rootViewModel2: RootViewModel2,
    onAddProductClicked: () -> Unit,
    onQrScanClicked: () -> Unit
) {
    val productSearchMode by rootViewModel.productSearchMode

    val productSearchText by rootViewModel.productSearchText

    // val productName by rootViewModel.productName

    val barcode by rootViewModel.barCode

    val qty by rootViewModel.qty

    val unit by rootViewModel.unit

    val rate by rootViewModel.rate

    val disc by rootViewModel.disc

    val tax by rootViewModel.tax

    val net by rootViewModel.net

    val interactionSource = remember {
        MutableInteractionSource()
    }

    var showDropDownMenu by remember {
        mutableStateOf(false)
    }





    Column() {
        // First row include product name and barcode.
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 8.dp),
        ) {
            // Product name
            Box(
                modifier = Modifier
                    .weight(2f)
                    .padding(end = 4.dp)
            ) {
                TextFieldDefaults.OutlinedTextFieldDecorationBox(
                    value = productSearchText,
                    innerTextField = {
                        BasicTextField(
                            value = productSearchText,
                            onValueChange = { value ->
                                rootViewModel2.setProductSearchText(value)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            readOnly = !productSearchMode,
                            maxLines = 1,
                            textStyle = TextStyle(
                                color = if (productSearchMode) MaterialTheme.colors.onBackground else MaterialTheme.colors.primary
                            )
                        )
                    },
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                    label = {
                        Text(text = "Product Name", fontSize = 14.sp)
                    },
                    contentPadding = TextFieldDefaults.outlinedTextFieldPadding(
                        start = 8.dp, top = 8.dp, bottom = 8.dp, end = 8.dp
                    ),
                    trailingIcon = {
                        /*Row {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = null,
                                tint = MaterialTheme.colors.error,
                                modifier = Modifier.clickable {
                                    navHostController.navigate(RootNavScreens.ProductListScreen.route)
                                }
                            )*/
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.clickable {
                                navHostController.navigate(RootNavScreens.AddProductScreen.route)
                            }
                        )
                        // }
                    }

                )
            }

            // Barcode. Read only
            Box(
                modifier = Modifier
                    .weight(1.2f)
                    .padding(start = 4.dp)
            ) {
                TextFieldDefaults.OutlinedTextFieldDecorationBox(
                    value = barcode,
                    innerTextField = {
                        BasicTextField(
                            value = barcode,
                            onValueChange = {

                            },
                            modifier = Modifier.fillMaxWidth(),
                            readOnly = true,
                            textStyle = TextStyle(
                                color = if (productSearchMode) MaterialTheme.colors.onBackground else MaterialTheme.colors.primary
                            )
                        )
                    },
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                    label = {
                        Text(text = "Barcode", fontSize = 14.sp)
                    },
                    contentPadding = TextFieldDefaults.outlinedTextFieldPadding(
                        start = 8.dp, top = 8.dp, bottom = 8.dp, end = 8.dp
                    ),
                    trailingIcon = {
                        Row(modifier = Modifier.padding(top = 8.dp)) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_qr_code_scanner_24),
                                contentDescription = null,
                                tint = MaterialTheme.colors.error,
                                modifier = Modifier.clickable {
                                    rootViewModel2.setProductSearchMode(false)
                                    rootViewModel.resetSelectedProduct()
                                    onQrScanClicked()
                                }
                            )

                        }
                    }

                )
            }
        }


        // Product details row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 0.dp),
        ) {
            // Product Quantity box
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        end = 2.dp
                    )
            ) {
                TextFieldDefaults.OutlinedTextFieldDecorationBox(
                    value = qty,
                    innerTextField = {
                        BasicTextField(
                            value = qty,
                            onValueChange = { value ->
                                rootViewModel.setQty(value)
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Decimal,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    hideKeyboard()
                                }
                            ),
                            textStyle = TextStyle(
                                textAlign = TextAlign.Center,
                                color = if (productSearchMode) MaterialTheme.colors.onBackground else MaterialTheme.colors.primary
                            ),
                            readOnly = !(productSearchText.isNotEmpty() && productSearchText.isNotBlank())
                        )
                    },
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                    label = {
                        Text(
                            text = "Qty",
                            fontSize = 10.sp,
                            textAlign = if (qty.isEmpty()) TextAlign.Center else TextAlign.Start,
                            modifier = if (qty.isEmpty()) Modifier.fillMaxWidth() else Modifier
                        )
                    },
                    contentPadding = TextFieldDefaults.outlinedTextFieldPadding(
                        start = if (qty.isEmpty()) 8.dp else 2.dp,
                        top = 8.dp,
                        bottom = 8.dp,
                        end = if (qty.isEmpty()) 8.dp else 2.dp,
                    ),
                )
            }

            // Product Unit box
            Box(
                modifier = Modifier
                    .weight(1.5f)
                    .padding(
                        start = 2.dp, end = 2.dp
                    )
            ) {
                TextFieldDefaults.OutlinedTextFieldDecorationBox(
                    value = unit,
                    innerTextField = {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = unit,
                                modifier = Modifier.weight(1f),
                                fontSize = 14.sp,
                                color = if (productSearchMode) MaterialTheme.colors.onBackground else MaterialTheme.colors.primary
                            )
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = null,
                                tint = MaterialTheme.colors.error,
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        if (productSearchText.isNotEmpty() || productSearchText.isNotBlank()) {
                                            showDropDownMenu = true
                                        }
                                    }
                            )
                        }
                    },
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                    label = {
                        Text(text = "Unit", fontSize = 10.sp)
                    },
                    contentPadding = TextFieldDefaults.outlinedTextFieldPadding(
                        start = 8.dp, top = 4.dp, bottom = 4.dp, end = 0.dp
                    ),
                )
                DropdownMenu(
                    expanded = showDropDownMenu,
                    onDismissRequest = {
                        showDropDownMenu = false
                    },
                    modifier = Modifier.width(60.dp)
                ) {
                    ProductUnit.values().forEach { productUnit ->
                        DropdownMenuItem(
                            onClick = {
                                rootViewModel.setUnit(productUnit)
                                showDropDownMenu = false
                            },
                            contentPadding = PaddingValues(all = 8.dp)
                        ) {
                            Text(text = productUnit.name)
                        }
                    }
                }
            }

            // Rate box
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = 2.dp, end = 2.dp
                    )
            ) {
                TextFieldDefaults.OutlinedTextFieldDecorationBox(
                    value = rate,
                    innerTextField = {
                        BasicTextField(
                            value = rate,
                            onValueChange = {

                            },
                            textStyle = TextStyle(
                                textAlign = TextAlign.Center,
                                color = if (productSearchMode) MaterialTheme.colors.onBackground else MaterialTheme.colors.primary
                            ),
                            readOnly = true
                        )
                    },
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                    label = {
                        Text(
                            text = "Rate",
                            fontSize = 10.sp,
                        )
                    },
                    contentPadding = TextFieldDefaults.outlinedTextFieldPadding(
                        start = if (rate.isEmpty()) 8.dp else 2.dp,
                        top = 8.dp,
                        bottom = 8.dp,
                        end = if (rate.isEmpty()) 8.dp else 2.dp,
                    ),
                )
            }

            // Discount box
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = 2.dp, end = 2.dp
                    )
            ) {
                TextFieldDefaults.OutlinedTextFieldDecorationBox(
                    value = disc,
                    innerTextField = {
                        BasicTextField(
                            value = disc,
                            onValueChange = { value ->
                                rootViewModel.setDisc(value)
                            },
                            textStyle = TextStyle(
                                textAlign = TextAlign.Center,
                                color = if (productSearchMode) MaterialTheme.colors.onBackground else MaterialTheme.colors.primary
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Decimal,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    hideKeyboard()
                                }
                            ),
                            readOnly = !(productSearchText.isNotEmpty() && productSearchText.isNotBlank())
                        )
                    },
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                    label = {
                        Text(text = "Disc", fontSize = 10.sp)
                    },
                    contentPadding = TextFieldDefaults.outlinedTextFieldPadding(
                        start = if (disc.isEmpty()) 8.dp else 2.dp,
                        top = 8.dp,
                        bottom = 8.dp,
                        end = if (disc.isEmpty()) 8.dp else 2.dp,
                    ),
                )
            }

            // Tax percentage box. read only
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = 2.dp, end = 2.dp
                    )
            ) {
                TextFieldDefaults.OutlinedTextFieldDecorationBox(
                    value = tax,
                    innerTextField = {
                        BasicTextField(
                            value = tax,
                            onValueChange = {

                            },
                            textStyle = TextStyle(
                                textAlign = TextAlign.Center,
                                color = if (productSearchMode) MaterialTheme.colors.onBackground else MaterialTheme.colors.primary
                            ),
                            readOnly = true
                        )
                    },
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                    label = {
                        Text(
                            text = if (tax.isEmpty()) "Tax" else "Tax %",
                            fontSize = 10.sp
                        )
                    },
                    contentPadding = TextFieldDefaults.outlinedTextFieldPadding(
                        start = if (tax.isEmpty()) 8.dp else 2.dp,
                        top = 8.dp,
                        bottom = 8.dp,
                        end = if (tax.isEmpty()) 8.dp else 2.dp,
                    ),
                )
            }

            // net amount box. read only
            Box(
                modifier = Modifier
                    .weight(1.5f)
                    .padding(
                        start = 2.dp
                    )
            ) {
                TextFieldDefaults.OutlinedTextFieldDecorationBox(
                    value = net.toString(),
                    innerTextField = {
                        BasicTextField(
                            value = net.toString(),
                            onValueChange = {
                            },
                            textStyle = TextStyle(
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colors.error
                            ),
                            readOnly = true
                        )
                    },
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                    label = {
                        Text(
                            text = "Net",
                            fontSize = 10.sp,
                            color = MaterialTheme.colors.error,
                        )
                    },
                    contentPadding = TextFieldDefaults.outlinedTextFieldPadding(
                        start = 8.dp,
                        top = 8.dp,
                        bottom = 8.dp,
                        end = 2.dp,
                    ),
                )
            }


        }
    }


}


