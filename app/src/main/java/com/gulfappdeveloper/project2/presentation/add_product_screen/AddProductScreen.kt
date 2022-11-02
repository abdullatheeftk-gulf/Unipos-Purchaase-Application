package com.gulfappdeveloper.project2.presentation.add_product_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.home_screen.util.ProductUnit
import kotlin.math.roundToInt

@Composable
fun AddProductScreen(
    rootViewModel: RootViewModel,
    navHostController: NavHostController,
    hideKeyboard: () -> Unit
) {

    val scaffoldState = rememberScaffoldState()

    var productName by remember {
        mutableStateOf("")
    }

    var qrCode by remember {
        mutableStateOf("")
    }

    var qty by remember {
        mutableStateOf("")
    }

    var unit by remember {
        mutableStateOf(ProductUnit.Kg)
    }

    var rate by remember {
        mutableStateOf("0")
    }

    var disc by remember {
        mutableStateOf("0")
    }

    var tax by remember {
        mutableStateOf("0")
    }

    var total by remember {
        mutableStateOf(0f)
    }

    var showDropDownMenu by remember {
        mutableStateOf(false)
    }






    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Add Product")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navHostController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        it.calculateTopPadding()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        ) {


            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "Product Name :-",
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(20.dp))
                TextField(
                    value = productName,
                    onValueChange = { value ->
                        productName = value
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier.weight(1f),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.background
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            hideKeyboard()
                        })
                )
            }

            Spacer(modifier = Modifier.height(20.dp))


            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "QR Code :-",
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(20.dp))
                TextField(
                    value = qrCode,
                    onValueChange = { value ->
                        qrCode = value
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier.weight(1f),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.background
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            hideKeyboard()
                        })
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "Quantity :-",
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(20.dp))
                TextField(
                    value = qty,
                    onValueChange = { value ->
                        qty = value
                        total = calculate(
                            qty = qty,
                            rate = rate,
                            tax = tax,
                            disc = disc
                        )

                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Decimal
                    ),
                    modifier = Modifier.weight(1f),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            total = calculate(
                                qty = qty,
                                rate = rate,
                                tax = tax,
                                disc = disc
                            )
                            hideKeyboard()
                        }
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.background
                    ),

                    )
            }


            Spacer(modifier = Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "Product Unit :-",
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    TextField(
                        value = unit.name,
                        onValueChange = {

                        },
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            IconButton(onClick = {
                                showDropDownMenu = true
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.KeyboardArrowDown,
                                    contentDescription = null,
                                    tint = MaterialTheme.colors.error
                                )
                            }
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = MaterialTheme.colors.background
                        )
                    )
                    DropdownMenu(
                        expanded = showDropDownMenu,
                        onDismissRequest = {
                            showDropDownMenu = false
                        }
                    ) {
                        ProductUnit.values().forEach { productUnit ->
                            DropdownMenuItem(onClick = {
                                unit = productUnit
                                showDropDownMenu = false
                            }) {
                                Text(text = productUnit.name)
                            }

                        }
                    }

                }
            }


            Spacer(modifier = Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "Rate :-",
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(20.dp))
                TextField(
                    value = rate,
                    onValueChange = { value ->
                        rate = value
                        total = calculate(
                            qty = qty,
                            rate = rate,
                            tax = tax,
                            disc = disc
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Decimal
                    ),
                    modifier = Modifier.weight(1f),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            total = calculate(
                                qty = qty,
                                rate = rate,
                                tax = tax,
                                disc = disc
                            )
                            hideKeyboard()
                        }
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.background
                    )
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "Tax in % :-",
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(20.dp))
                TextField(
                    value = tax,
                    onValueChange = { value ->
                        tax = value
                        total = calculate(
                            qty = qty,
                            rate = rate,
                            tax = tax,
                            disc = disc
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Decimal
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            total = calculate(
                                qty = qty,
                                rate = rate,
                                tax = tax,
                                disc = disc
                            )
                            hideKeyboard()
                        }
                    ),
                    modifier = Modifier.weight(1f),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.background
                    )
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "Discount :-",
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(20.dp))
                TextField(
                    value = disc,
                    onValueChange = { value ->
                        disc = value
                        total = calculate(
                            qty = qty,
                            rate = rate,
                            tax = tax,
                            disc = disc
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Decimal
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            hideKeyboard()
                            total = calculate(
                                qty = qty,
                                rate = rate,
                                tax = tax,
                                disc = disc
                            )
                        }
                    ),
                    modifier = Modifier.weight(1f),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.background
                    )
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "Net Amount :-",
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = total.toString(),
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colors.error,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center
                )
            }


            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    rootViewModel.setProductName(productName)
                    rootViewModel.setQrCode(qrCode)
                    rootViewModel.setQty(qty)
                    rootViewModel.setUnit(unit)
                    rootViewModel.setRate(rate)
                    rootViewModel.setTax(tax)
                    rootViewModel.setDisc(disc)
                    rootViewModel.calculateNet()
                    navHostController.popBackStack()
                }

            ) {
                Text(text = "Add")
            }
        }

    }

}

private fun calculate(
    qty: String,
    rate: String,
    tax: String,
    disc: String
): Float {
    var total: Float
    return try {
        total = qty.toFloat() * rate.toFloat()
        total += total * (tax.toFloat() / 100)
        total -= disc.toFloat()
        total = (total.roundToInt()*100)/100.00f
        total
    } catch (e: Exception) {
        e.printStackTrace()
        0f
    }
}
