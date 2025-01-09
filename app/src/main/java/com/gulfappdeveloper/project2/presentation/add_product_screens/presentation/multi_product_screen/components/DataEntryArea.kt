package com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.multi_product_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.window.PopupProperties
import com.gulfappdeveloper.project2.R
import com.gulfappdeveloper.project2.presentation.add_product_screens.AddProductMainViewModel
import com.gulfappdeveloper.project2.presentation.ui_util.ScanFrom
import com.gulfappdeveloper.project2.presentation.ui_util.screenSize
import com.gulfappdeveloper.project2.ui.theme.OrangeColor

@Composable
fun DataEntryArea(
    addProductMainViewModel: AddProductMainViewModel,
    hideKeyboard: () -> Unit,
    onDataValidationError: () -> Unit,
    onScanButtonClicked: (ScanFrom) -> Unit
) {
    val screenWidth = screenSize().value

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
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // First row
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 4.dp),
                contentAlignment = Alignment.Center
            ) {

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
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
                        Text(
                            text = "Unit",
                            fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                        )
                    },
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                    ),
                    isError = showUnitError,
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledLabelColor = MaterialTheme.colorScheme.onSurface,
                        disabledBorderColor = MaterialTheme.colorScheme.onSurface,
                        disabledTextColor = MaterialTheme.colorScheme.primary,
                        unfocusedTextColor = MaterialTheme.colorScheme.primary
                    ),
                )

                DropdownMenu(
                    modifier = Modifier.fillMaxWidth(),
                    expanded = showDropDownMenu,
                    onDismissRequest = {
                        showDropDownMenu = false
                    },
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
                            text = {
                                Text(
                                    text = unit.unitName,
                                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                                )
                            }
                        )
                    }
                }
            }

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 4.dp),
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
                        fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Decimal
                ),

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
        Spacer(modifier = Modifier.height(
            if(screenWidth<600) 0.dp else if(screenWidth>=600 && screenWidth<800) 6.dp else 10.dp
        ))
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
                Text(
                    text = "Product Name",
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
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
        Spacer(modifier = Modifier.height(
            if(screenWidth<600) 0.dp else if(screenWidth>=600 && screenWidth<800) 6.dp else 10.dp
        ))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedTextField(
                value = barcode,
                onValueChange = { value ->
                    showUnitError = false
                    showBarcodeError = false
                    showProductNameError = false
                    showPriceError = false
                    showQtyError = false

                    addProductMainViewModel.setMultiUnitBarcode(value)
                },
                enabled = true,
                modifier = Modifier
                    .weight(2f)
                    .padding(end = 4.dp),
                label = {
                    Text(
                        text = "Barcode",
                        fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                    )
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
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
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
                    Text(
                        text = "Sales Price",
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
                    }),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                ),
                modifier = Modifier
                    .weight(2f)
                    .padding(start = 4.dp),
                isError = showPriceError
            )

        }

        Spacer(modifier = Modifier.height(
            if(screenWidth<600) 4.dp else if(screenWidth>=600 && screenWidth<800) 8.dp else 16.dp
        ))
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
                fontSize = if (screenWidth < 600) 20.sp else if (screenWidth >= 600 && screenWidth < 800) 24.sp else 28.sp,
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
                    .width(
                        if (screenWidth < 600) 100.dp else if (screenWidth >= 600 && screenWidth < 800) 120.dp else 140.dp
                    )
                    .padding(start = 4.dp),
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = if(screenWidth<600) 14.sp else if(screenWidth>=600 && screenWidth<800) 18.sp else 22.sp
                ),
                placeholder = {
                    Text(
                        text = "0",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = if(screenWidth<600) 14.sp else if(screenWidth>=600 && screenWidth<800) 18.sp else 22.sp
                    )
                })
        }
        // Check box row
        Spacer(modifier = Modifier.height(
            if(screenWidth<600) 4.dp else if(screenWidth>=600 && screenWidth<800) 8.dp else 16.dp
        ))
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
                fontSize = if(screenWidth<600) 20.sp else if(screenWidth>=600 && screenWidth<800) 24.sp else 28.sp
            )
            Checkbox(
                modifier = Modifier.size(
                    if(screenWidth<600) 30.dp else if(screenWidth>=600 && screenWidth<800) 40.dp else 60.dp
                ),
                checked = isInclusive,
                onCheckedChange = { value ->
                    addProductMainViewModel.setMultiUnitIsInclusive(value)
                }
            )
        }

        Spacer(modifier = Modifier.height(
            if(screenWidth<600) 4.dp else if(screenWidth>=600 && screenWidth<800) 8.dp else 16.dp
        ))

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
                Text(
                    text = "Add To List",
                    fontSize = if(screenWidth<600) 14.sp else if(screenWidth>=600 && screenWidth<800) 18.sp else 22.sp
                )
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
                    color = MaterialTheme.colorScheme.surface,
                    fontSize = if(screenWidth<600) 14.sp else if(screenWidth>=600 && screenWidth<800) 18.sp else 22.sp
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
                Text(
                    text = "Clear List",
                    fontSize = if(screenWidth<600) 14.sp else if(screenWidth>=600 && screenWidth<800) 18.sp else 22.sp
                )
            }
        }



    }
}

