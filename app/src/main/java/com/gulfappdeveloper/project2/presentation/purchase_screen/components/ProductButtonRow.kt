package com.gulfappdeveloper.project2.presentation.purchase_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.ui_util.screenSize
import com.gulfappdeveloper.project2.ui.theme.OrangeColor

@Composable
fun ProductButtonRow(
    rootViewModel: RootViewModel,
    onProductAdded: () -> Unit,
    onProductNameError: (Boolean) -> Unit,
    onAdditionalDiscountAdded: () -> Unit,
    onFreightChargeAdded: () -> Unit,
    onQuantityError: (Boolean) -> Unit,
    onClearButtonClicked: () -> Unit,
    onBarcodeError: (Boolean) -> Unit,
) {
    val screenWidth = screenSize().value

    val productName by rootViewModel.productName
    val qty by rootViewModel.qty
    val barcode by rootViewModel.barCode

    var showDropDownMenu by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        Button(
            onClick = {
                if (productName.isEmpty()) {
                    onProductNameError(true)
                    return@Button
                }
                if (qty.isEmpty()) {
                    onQuantityError(true)
                    return@Button
                }
                if (barcode.isEmpty()) {
                    onBarcodeError(true)
                    return@Button
                }
                onProductAdded()

            },
            contentPadding = PaddingValues(
                horizontal = if (screenWidth < 600) 2.dp else if (screenWidth >= 600 && screenWidth < 800) 4.dp else 8.dp,
                vertical = if (screenWidth < 600) 8.dp else if (screenWidth >= 600 && screenWidth < 800) 12.dp else 16.dp
            ),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
            Text(
                text = "Add",
                fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
            )
        }
        Button(
            onClick = {
                rootViewModel.resetSelectedProduct()
                onClearButtonClicked()
            },
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp),
            contentPadding = PaddingValues(
                horizontal = if (screenWidth < 600) 2.dp else if (screenWidth >= 600 && screenWidth < 800) 4.dp else 8.dp,
                vertical = if (screenWidth < 600) 8.dp else if (screenWidth >= 600 && screenWidth < 800) 12.dp else 16.dp
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = OrangeColor
            )
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                modifier = Modifier.scale(0.8f),
                tint = MaterialTheme.colorScheme.surface
            )
            Text(
                text = "Clear",
                color = MaterialTheme.colorScheme.surface,
                fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
            )

        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { showDropDownMenu = true },
                contentPadding = PaddingValues(
                    horizontal = if (screenWidth < 600) 2.dp else if (screenWidth >= 600 && screenWidth < 800) 4.dp else 8.dp,
                    vertical = if (screenWidth < 600) 8.dp else if (screenWidth >= 600 && screenWidth < 800) 12.dp else 16.dp
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "More",
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                )
                Spacer(modifier = Modifier.width(2.dp))
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = null,
                )
            }
            DropdownMenu(
                expanded = showDropDownMenu,
                onDismissRequest = {
                    showDropDownMenu = false
                }
            ) {
                DropdownMenuItem(
                    onClick = {
                        rootViewModel.clearProductList()
                        showDropDownMenu = false
                    },
                    text = {
                        Text(
                            text = "Clear Product List",
                            fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                        )
                    }
                )
                DropdownMenuItem(
                    onClick = {
                        onAdditionalDiscountAdded()
                        showDropDownMenu = false
                    },
                    text = {
                        Text(
                            text = "Add Additional Discount",
                            fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                        )
                    }
                )
                DropdownMenuItem(
                    onClick = {
                        onFreightChargeAdded()
                        showDropDownMenu = false
                    },
                    text = {
                        Text(
                            text = "Add Freight Charge",
                            fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                        )
                    }
                )
            }

        }

    }
}


