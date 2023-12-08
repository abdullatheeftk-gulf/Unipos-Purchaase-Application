package com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.multi_product_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.R
import com.gulfappdeveloper.project2.presentation.add_product_screens.AddProductMainViewModel
import com.gulfappdeveloper.project2.presentation.ui_util.ScanFrom
import com.gulfappdeveloper.project2.ui.theme.OrangeColor

@Composable
fun DataEntryArea(
    addProductMainViewModel: AddProductMainViewModel,
    hideKeyboard: () -> Unit,
    onDataValidationError: () -> Unit,
    onScanButtonClicked: (ScanFrom) -> Unit
) {

    val selectedUnit by addProductMainViewModel.selectedMultiUnit

    var showUnitError by remember {
        mutableStateOf(false)
    }

    val focusManager = LocalFocusManager.current

    val productName by addProductMainViewModel.multiUnitProductName

    var showProductNameError by remember {
        mutableStateOf(false)
    }

    val barcode by addProductMainViewModel.multiUnitBarcode

    var showBarcodeError by remember {
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
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // First row
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(end = 4.dp)
            ) {
                OutlinedTextField(
                    value = selectedUnit?.unitName ?: "",
                    onValueChange = {},
                    enabled = false,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                showDropDownMenu = true
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.KeyboardArrowDown,
                                contentDescription = null,
                                tint = OrangeColor
                            )
                        }
                    },
                    label = {
                        Text(text = "Unit")
                    },
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.primary
                    ),
                    isError = showUnitError,
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                    ),
                )
                DropdownMenu(
                    expanded = showDropDownMenu,
                    onDismissRequest = {
                        showDropDownMenu = false
                    }
                ) {
                    unitList.forEach { unit ->
                        DropdownMenuItem(
                            onClick = {
                                showUnitError = false
                                showBarcodeError = false
                                showProductNameError = false
                                showPriceError = false
                                showQtyError = false

                                addProductMainViewModel.setSelectedMultiUnit(unit)
                                showDropDownMenu = false
                            },
                            text = { Text(text = unit.unitName) }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = qty,
                onValueChange = { value ->

                    showUnitError = false
                    showBarcodeError = false
                    showProductNameError = false
                    showPriceError = false
                    showQtyError = false

                    addProductMainViewModel.setMultiUnitQty(value)
                },
                label = {
                    Text(
                        text = "Qty",
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Decimal
                ),
                modifier = Modifier
                    .weight(2f)
                    .padding(start = 4.dp),
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        hideKeyboard()
                    }
                ),
                isError = showQtyError
            )

        }

        // Second row
        OutlinedTextField(
            value = productName,
            onValueChange = { value ->

                showUnitError = false
                showBarcodeError = false
                showProductNameError = false
                showPriceError = false
                showQtyError = false

                addProductMainViewModel.setMultiUnitProductName(value)
            },
            modifier = Modifier
                .fillMaxWidth(),
            label = {
                Text(text = "Product Name")
            },
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.primary
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                hideKeyboard()
            }),
            maxLines = 2,
            isError = showProductNameError
        )

        // Third row
        Row(modifier = Modifier.fillMaxWidth()) {

            OutlinedTextField(
                value = barcode,
                onValueChange = { value ->
                    showUnitError = false
                    showBarcodeError = false
                    showProductNameError = false
                    showPriceError = false
                    showQtyError = false

                    addProductMainViewModel.setMultiUnitBarcode(value)
                }, enabled = true,
                modifier = Modifier
                    .weight(2f)
                    .padding(end = 4.dp),
                label = {
                    Text(text = "Barcode")
                },
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Characters, imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        hideKeyboard()
                    }),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.primary
                ),
                isError = showBarcodeError,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            onScanButtonClicked(ScanFrom.MULTI_UNIT_SCREEN)
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_barcode_scanner_24),
                            contentDescription = null,
                            tint = OrangeColor
                        )
                    }
                }
            )


            OutlinedTextField(
                value = price,
                onValueChange = { value ->

                    showUnitError = false
                    showBarcodeError = false
                    showProductNameError = false
                    showPriceError = false
                    showQtyError = false

                    addProductMainViewModel.setMultiUnitPrice(value)
                },
                label = {
                    Text(text = "Sales Price")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Decimal
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        hideKeyboard()
                    }),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.primary, fontSize = 20.sp
                ),
                modifier = Modifier
                    .weight(2f)
                    .padding(start = 4.dp),
                isError = showPriceError
            )

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
                fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = " : ",
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
            )
            OutlinedTextField(
                value = openingStock,
                onValueChange = { value ->
                    addProductMainViewModel.setMultiUnitOpeningStock(value)
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done, keyboardType = KeyboardType.Decimal
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        hideKeyboard()
                    }
                ),
                modifier = Modifier
                    .width(100.dp)
                    .padding(start = 4.dp),
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary
                ),
                placeholder = {
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
                color = MaterialTheme.colorScheme.primary,
                fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize
            )
            Checkbox(
                checked = isInclusive,
                onCheckedChange = { value ->
                    addProductMainViewModel.setMultiUnitIsInclusive(value)
                }
            )
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
                        showBarcodeError = true
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
                    focusManager.clearFocus()
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp)
            ) {
                Text(text = "Add To List")
            }
            Button(
                onClick = {
                    addProductMainViewModel.clearMultiUnitDataEntryArea()
                    focusManager.clearFocus()

                    showUnitError = false
                    showBarcodeError = false
                    showProductNameError = false
                    showPriceError = false
                    showQtyError = false
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = OrangeColor
                )
            ) {
                Text(
                    text = "Clear",
                    color = MaterialTheme.colorScheme.surface
                )
            }
            Button(
                onClick = {
                    addProductMainViewModel.clearMultiUnitProductList()
                    focusManager.clearFocus()
                    showUnitError = false
                    showBarcodeError = false
                    showProductNameError = false
                    showPriceError = false
                    showQtyError = false

                }, modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp)
            ) {
                Text(text = "Clear List")
            }
        }


    }
}

