package com.gulfappdeveloper.project2.presentation.home_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.ui.theme.OrangeColor

@Composable
fun ProductButtonRow(
    rootViewModel: RootViewModel,
    onProductAdded: () -> Unit,
    onProductNameError: (Boolean) -> Unit,
    onQuantityError: (Boolean) -> Unit,
    onBarcodeError: (Boolean) -> Unit
) {
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
                horizontal = 2.dp
            ),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
            Text(text = "Add")
        }
        Button(
            onClick = {
                rootViewModel.resetSelectedProduct()
            },
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp),
            contentPadding = PaddingValues(
                horizontal = 2.dp
            ),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.OrangeColor
            )
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                modifier = Modifier.scale(0.8f)
            )
            Text(text = "Clear")

        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { showDropDownMenu = true },
                contentPadding = PaddingValues(horizontal = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "More")
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
                    onClick = { showDropDownMenu = false }
                ) {
                    Button(onClick = { showDropDownMenu = false }) {
                        Text(text = "Clear Product List")
                    }
                }
            }

        }

    }
}


