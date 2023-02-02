package com.gulfappdeveloper.project2.presentation.add_product_main_screen.presentation.multi_product_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.presentation.add_product_main_screen.AddProductMainViewModel
import com.gulfappdeveloper.project2.ui.theme.OrangeColor

@Composable
fun DataEntryArea(
    addProductMainViewModel: AddProductMainViewModel,
    hideKeyboard: () -> Unit,
    onDataValidationError: () -> Unit
) {


    val selectedUnit by addProductMainViewModel.selectedMultiUnit

    var showUnitError by remember {
        mutableStateOf(false)
    }

    val productName by addProductMainViewModel.multiUnitProductName

    var showProductNameError by remember {
        mutableStateOf(false)
    }

    val barcode by addProductMainViewModel.multiUnitBarcode

    var showbarcodeError by remember {
        mutableStateOf(false)
    }

    val qty by addProductMainViewModel.multiUnitQty

    var showQtyError by remember {
        mutableStateOf(false)
    }

    val price by addProductMainViewModel.multiUnitPrice

    var showPriceError by remember {
        mutableStateOf(false)
    }

    val openingStock by addProductMainViewModel.multiUnitOpeningStock


    val isInclusive by addProductMainViewModel.multiUnitIsInclusive

    var showDropDownMenu by remember {
        mutableStateOf(false)
    }

    val unitList = addProductMainViewModel.multiUnitsList

    Column(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // First row
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(end = 4.dp)
            ) {
                OutlinedTextField(value = selectedUnit?.unitName ?: "",
                    onValueChange = {},
                    enabled = false,
                    trailingIcon = {
                        IconButton(onClick = { showDropDownMenu = true }) {
                            Icon(
                                imageVector = Icons.Filled.KeyboardArrowDown,
                                contentDescription = null,
                                tint = MaterialTheme.colors.OrangeColor
                            )
                        }
                    },
                    label = {
                        Text(text = "Unit")
                    },
                    textStyle = TextStyle(
                        color = MaterialTheme.colors.primary
                    ),
                    isError = showUnitError,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        disabledLabelColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
                    )
                )
                DropdownMenu(expanded = showDropDownMenu, onDismissRequest = {

                    showDropDownMenu = false
                }) {
                    unitList.forEachIndexed { index, unit ->
                        DropdownMenuItem(onClick = {
                            showUnitError = false
                            addProductMainViewModel.setSelectedMultiUnit(unit)
                            showDropDownMenu = false
                        }) {
                            Text(text = unit.unitName)
                        }
                    }
                }
            }

            OutlinedTextField(value = qty, onValueChange = { value ->
                showQtyError = false
                addProductMainViewModel.setMultiUnitQty(value)
            }, label = {
                Text(
                    text = "Qty",
                )
            }, keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done, keyboardType = KeyboardType.Decimal
            ), modifier = Modifier
                .weight(2f)
                .padding(start = 4.dp), textStyle = TextStyle(
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primary,
                fontSize = 20.sp
            ),

                keyboardActions = KeyboardActions(onDone = {
                    hideKeyboard()
                }), isError = showQtyError)

        }

        // Second row
        OutlinedTextField(value = productName, onValueChange = { value ->
            showProductNameError = false
            addProductMainViewModel.setMultiUnitProductName(value)
        }, modifier = Modifier.fillMaxWidth(), label = {
            Text(text = "Product Name")
        }, textStyle = TextStyle(
            color = MaterialTheme.colors.primary
        ), keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ), keyboardActions = KeyboardActions(onDone = {
            hideKeyboard()
        }), maxLines = 2, isError = showProductNameError)

        // Third row
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(value = barcode, onValueChange = { value ->
                showbarcodeError = false
                addProductMainViewModel.setMultiUnitBarcode(value)
            }, enabled = true, modifier = Modifier
                .weight(2f)
                .padding(end = 4.dp), label = {
                Text(text = "Barcode")
            }, singleLine = true, maxLines = 1, keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Characters, imeAction = ImeAction.Done
            ), keyboardActions = KeyboardActions(onDone = {
                hideKeyboard()
            }), textStyle = TextStyle(
                color = MaterialTheme.colors.primary
            ), isError = showbarcodeError)


            OutlinedTextField(value = price, onValueChange = { value ->
                showPriceError = false
                addProductMainViewModel.setMultiUnitPrice(value)
            }, label = {
                Text(text = "Sales Price")
            }, keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done, keyboardType = KeyboardType.Decimal
            ), keyboardActions = KeyboardActions(onDone = {
                hideKeyboard()
            }), textStyle = TextStyle(
                color = MaterialTheme.colors.primary, fontSize = 20.sp
            ), modifier = Modifier
                .weight(2f)
                .padding(start = 4.dp), isError = showPriceError)

        }
        // 4 th row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Opening Stock",
                fontSize = 20.sp,
                fontStyle = MaterialTheme.typography.h6.fontStyle,
                color = MaterialTheme.colors.primary
            )
            Text(
                text = " : ",
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontStyle = MaterialTheme.typography.h6.fontStyle,
            )
            OutlinedTextField(value = openingStock, onValueChange = { value ->
                addProductMainViewModel.setMultiUnitOpeningStock(value)
            }, keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done, keyboardType = KeyboardType.Decimal
            ), modifier = Modifier
                .width(100.dp)
                .padding(start = 4.dp), textStyle = TextStyle(
                textAlign = TextAlign.Center, color = MaterialTheme.colors.primary
            ), placeholder = {
                Text(
                    text = "0", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
                )
            })
        }
        // Check box row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Is Inclusive",
                color = MaterialTheme.colors.primary,
                fontStyle = MaterialTheme.typography.h6.fontStyle,
                fontSize = MaterialTheme.typography.h6.fontSize
            )
            Checkbox(checked = isInclusive, onCheckedChange = { value ->
                addProductMainViewModel.setMultiUnitIsInclusive(value)
            })
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    var haveError = false
                    if (selectedUnit == null) {
                        showUnitError = true
                        haveError = true
                    }
                    if (productName.isEmpty() || productName.isBlank()) {
                        showProductNameError = true
                        haveError = true
                    }
                    if (qty.isBlank() || qty.isEmpty()) {
                        showQtyError = true
                        haveError = true
                    }
                    if (barcode.isEmpty() || barcode.isBlank()) {
                        showbarcodeError = true
                        haveError = true
                    }
                    if (price.isEmpty() || price.isBlank()) {
                        showPriceError = true
                        haveError = true
                    }

                    if (haveError) {
                        onDataValidationError()
                        return@Button
                    }

                    addProductMainViewModel.onAddToMultiUnitListClicked()
                }, modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp)
            ) {
                Text(text = "Add To List")
            }
            Button(
                onClick = {
                    addProductMainViewModel.clearMultiUnitDataEntryArea()
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.OrangeColor
                )
            ) {
                Text(
                    text = "Clear",
                )
            }
            Button(
                onClick = {
                    addProductMainViewModel.clearMultiUnitProductList()
                }, modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp)
            ) {
                Text(text = "Clear List")
            }
        }


    }
}

/*
@Preview(showBackground = true)
@Composable
fun DataEntryPrev() {
    DataEntryArea()
}*/
