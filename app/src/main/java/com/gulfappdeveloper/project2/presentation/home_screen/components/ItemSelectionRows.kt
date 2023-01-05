package com.gulfappdeveloper.project2.presentation.home_screen.components

import android.util.Log
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.R
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.home_screen.HomeScreenViewModel
import com.gulfappdeveloper.project2.presentation.home_screen.util.ProductUnit

private const val TAG = "ItemSelectionRows"

@Composable
fun ItemSelectionRows(
    onFocusOnBasicTextField: (Boolean) -> Unit,
    homeScreenViewModel: HomeScreenViewModel,
    navHostController: NavHostController,
    hideKeyboard: () -> Unit,
    rootViewModel: RootViewModel
) {

    val focusManager = LocalFocusManager.current

    val selectedProduct by rootViewModel.selectedProduct

    var productItem by remember {
        mutableStateOf("")
    }
    var barcode by remember {
        mutableStateOf("")
    }

    var interactionSource = remember {
        MutableInteractionSource()
    }

    var qty by remember {
        mutableStateOf("")
    }
    var unit by remember {
        mutableStateOf("Nos")
    }

    var disc by remember {
        mutableStateOf("")
    }

    var tax by remember {
        mutableStateOf("")
    }

    var net by remember {
        mutableStateOf("")
    }

    var showDropDownMenu by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            /* OutlinedTextField(
                 value = selectedProduct?.productName ?: "",
                 onValueChange = {

                 },
                 placeholder = {
                     Text(text = "ProductItem")
                 },
                 modifier = Modifier
                     .weight(2.5f)
                     .padding(horizontal = 4.dp)
                     .onFocusEvent {
                         // homeScreenViewModel.onFocused(it.isFocused)

                         *//*onFocusOnBasicTextField(it.isFocused)
                    if (it.hasFocus) {
                        focusManager.clearFocus()
                    }*//*
                    },
                trailingIcon = {

                    IconButton(onClick = {
                        navHostController.navigate(RootNavScreens.ProductListScreen.route)
                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = null,
                            tint = MaterialTheme.colors.error
                        )
                    }
                }
            )*/
            Row(
                modifier = Modifier
                    .height(60.dp)
                    .weight(2f)
                    .padding(all = 4.dp)
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = MaterialTheme.shapes.medium
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Product name",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .weight(5f)
                        .padding(horizontal = 10.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colors.error
                )
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null,
                )
            }

            Row(
                modifier = Modifier
                    .height(60.dp)
                    .weight(1f)
                    .padding(all = 4.dp)
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = MaterialTheme.shapes.medium
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "QR Code", fontSize = 16.sp,
                    modifier = Modifier
                        .weight(2f)
                        .padding(horizontal = 4.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Icon(
                    painterResource(id = R.drawable.ic_baseline_qr_code_scanner_24),
                    contentDescription = null,
                    modifier = Modifier.weight(
                        1f
                    )
                )
            }

            /*  OutlinedTextField(
                  value = selectedProduct?.barcode ?: "",
                  onValueChange = {

                  },
                  trailingIcon = {
                      Icon(
                          painter = painterResource(id = R.drawable.ic_baseline_qr_code_scanner_24),
                          contentDescription = null,
                      )
                  },
                  placeholder = {
                      Text(text = "QR code")
                  },
                  modifier = Modifier
                      .weight(1.2f)
                      .padding(horizontal = 4.dp)
              )*/
        }
        Spacer(modifier = Modifier.height(4.dp))

        Row {
            Box(
                modifier = Modifier
                    .height(35.dp)
                    .weight(1f)
                    .padding(
                        start = 4.dp, end = 1.dp
                    )
                    .border(
                        color = Color.LightGray, width = 1.dp, shape = MaterialTheme.shapes.medium
                    ), contentAlignment = Alignment.Center
            ) {
                BasicTextField(modifier = Modifier.padding(all = 4.dp),
                    value = qty,
                    onValueChange = {
                        qty = it
                    },
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp,
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        hideKeyboard()
                    }),
                    singleLine = true,
                    decorationBox = {
                        Box(
                            modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                        ) {
                            if (qty.isEmpty()) {
                                Text(
                                    text = "Qty",
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.alpha(ContentAlpha.medium)
                                )
                            }
                        }
                        it()
                    })
            }


            Box(
                modifier = Modifier
                    .weight(2f)
                    .padding(
                        start = 1.dp, end = 1.dp
                    )
                    .border(
                        color = Color.LightGray, width = 1.dp, shape = MaterialTheme.shapes.medium
                    )
            ) {
                Row(
                    modifier = Modifier.padding(6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    DropdownMenu(
                        expanded = showDropDownMenu, onDismissRequest = {
                            showDropDownMenu = false
                        }, offset = DpOffset(x = 0.dp, y = 0.dp)
                    ) {
                        ProductUnit.values().forEach { productUnit ->
                            DropdownMenuItem(
                                onClick = {
                                    unit = productUnit.name
                                    showDropDownMenu = false
                                },
                                contentPadding = PaddingValues(horizontal = 4.dp),
                                modifier = Modifier
                                    .width(80.dp)
                                    .height(30.dp)
                            ) {
                                Text(text = productUnit.name)
                            }
                        }

                    }
                    Text(
                        text = selectedProduct?.unitName ?: unit,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .weight(3f)
                            .alpha(
                                alpha = if (selectedProduct == null) ContentAlpha.medium else ContentAlpha.high
                            )
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = MaterialTheme.colors.error,
                        modifier = Modifier
                            .weight(1f)
                            .alpha(alpha = if (selectedProduct == null) ContentAlpha.disabled else ContentAlpha.high)
                            .clickable {
                                showDropDownMenu = true
                            },
                    )
                }

            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = 1.dp, end = 1.dp
                    )
                    .border(
                        color = Color.LightGray, width = 1.dp, shape = MaterialTheme.shapes.medium
                    )
            ) {
                BasicTextField(
                    value = if (selectedProduct == null) "Rate" else selectedProduct?.rate.toString(),
                    onValueChange = {

                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .alpha(alpha = if (selectedProduct == null) ContentAlpha.medium else ContentAlpha.high),
                    readOnly = true,
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center
                    )
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = 1.dp, end = 1.dp
                    )
                    .border(
                        color = Color.LightGray, width = 1.dp, shape = MaterialTheme.shapes.medium
                    )
            ) {
                BasicTextField(
                    value = disc,
                    onValueChange = {
                        disc = it
                    },
                    modifier = Modifier.padding(8.dp),
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        hideKeyboard()
                    }),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done, keyboardType = KeyboardType.Decimal
                    ),
                    decorationBox = {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            if (disc.isEmpty()) {
                                Text(text = "Disc", fontSize = 14.sp)
                            }
                        }
                        it()
                    }
                )
            }


            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = 1.dp, end = 1.dp
                    )
                    .border(
                        color = Color.LightGray,
                        width = 1.dp,
                        shape = MaterialTheme.shapes.medium
                    )
            ) {
                val value = if (selectedProduct == null) tax else "${selectedProduct?.vatPercentage}%"
                BasicTextField(value = value,
                    onValueChange = { typedValue ->
                        tax = typedValue
                    },
                    enabled = selectedProduct == null,
                    modifier = Modifier.padding(8.dp),
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center, color = MaterialTheme.colors.error
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done, keyboardType = KeyboardType.Decimal
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        hideKeyboard()
                        tax = "$tax%"
                    }),
                    decorationBox = {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            if (tax.isEmpty() && selectedProduct == null) {
                                Text(
                                    text = "Tax",
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colors.error
                                )
                            }
                        }
                        it()
                    })
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = 1.dp, end = 4.dp
                    )
                    .border(
                        color = Color.LightGray, width = 1.dp, shape = MaterialTheme.shapes.medium
                    )
            ) {
                try {
                    net =
                        ((qty.toFloat() * selectedProduct?.rate!!) + (qty.toFloat() * selectedProduct?.rate!!) * selectedProduct?.vatPercentage!! / 100 - disc.toFloat()).toString()
                    Log.i(TAG, "ItemSelectionRows: $net")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                BasicTextField(
                    value = net,
                    onValueChange = {

                    },
                    enabled = false,
                    modifier = Modifier.padding(8.dp),
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.error
                    ),
                    decorationBox = {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            if (selectedProduct == null || qty.isEmpty()) {
                                Text(
                                    text = "Net",
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colors.error
                                )
                            }
                        }
                        it()
                    }
                )
            }


        }
    }

}


/*
@Preview(showBackground = true)
@Composable
fun ItemSelectionRowsPrev() {
    ItemSelectionRows()
}*/
