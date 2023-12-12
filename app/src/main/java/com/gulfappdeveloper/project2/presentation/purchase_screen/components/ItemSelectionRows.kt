package com.gulfappdeveloper.project2.presentation.purchase_screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.R
import com.gulfappdeveloper.project2.navigation.root.RootNavScreens
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.ui_util.ScanFrom
import com.gulfappdeveloper.project2.ui.theme.OrangeColor
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemSelectionRows(
    rootViewModel: RootViewModel,
    navHostController: NavHostController,
    hideKeyboard: () -> Unit,
    onScanButtonClicked: (ScanFrom) -> Unit,
    onProductNameError: () -> Unit,
    onQuantityError: () -> Unit,
    showProductNameError: Boolean,
    showQuantityError: Boolean,
) {
    val unitsList = rootViewModel.unitsList

    val productSearchMode by rootViewModel.productSearchMode

    val productName by rootViewModel.productName

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



    var roundOff:Float


    Column() {
        // First row include product name and barcode.
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Product name
            Box(
                modifier = Modifier
                    .weight(2f)
                    //.border(width = .5.dp, color = Color.LightGray, shape = RoundedCornerShape(25))
                    .padding(end = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                OutlinedTextFieldDefaults.DecorationBox(
                    value = productName,
                    innerTextField = {
                        BasicTextField(
                            value = productName,
                            onValueChange = { value ->
                                onProductNameError()
                                // if length is less than or equal to 3, it will call
                                if (value.length <= 3) {
                                    rootViewModel.setProductName(
                                        value,
                                        isItFromHomeScreen = true,
                                        requiredSearch = true
                                    )
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            readOnly = !productSearchMode,
                            //maxLines = 2,
                            textStyle = TextStyle(
                                color = if (productSearchMode) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.primary
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    hideKeyboard()
                                }
                            ),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done
                            ),
                        )
                    },
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                    isError = showProductNameError,
                    label = {
                        Text(text = "Product Name", fontSize = 14.sp,)
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.clickable {
                                onProductNameError()
                                rootViewModel.setProductSearchMode(true)
                                rootViewModel.resetSelectedProduct()
                                // navigation to AddProductScreen from the purchase screen
                                rootViewModel.setNavFrom(false)
                                navHostController.navigate(RootNavScreens.AddProductMainScreen.route)
                            }
                        )
                    },
                    /*colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.LightGray,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        disabledBorderColor = Color.LightGray,
                        focusedContainerColor = Color.Blue,
                        unfocusedContainerColor = Color.Green
                    ),*/
                    contentPadding = OutlinedTextFieldDefaults.contentPadding(
                        start = 8.dp,
                        top = 8.dp,
                        end = 8.dp,
                        bottom = 8.dp,
                    ),
                    container = {
                       // OutlinedTextFieldDefaults.ContainerBox(enabled, isError, interactionSource, colors)
                                OutlinedTextFieldDefaults.ContainerBox(
                                    enabled = true,
                                    isError = false,
                                    interactionSource = interactionSource,
                                    colors = OutlinedTextFieldDefaults.colors(
                                        unfocusedBorderColor = Color.Black.copy(alpha = 0.4f),
                                        focusedBorderColor = Color.Blue,
                                        disabledBorderColor = Color.Black,
                                    ),
                                    focusedBorderThickness = 0.5.dp,
                                    shape = RoundedCornerShape(20)
                                )
                    },
                )
            }

            // Barcode
            Box(
                modifier = Modifier
                    .weight(1.2f)
                    .padding(start = 4.dp)
            ) {
                OutlinedTextFieldDefaults.DecorationBox(
                    value = barcode,
                    innerTextField = {
                        BasicTextField(
                            value = barcode,
                            onValueChange = { value ->
                                rootViewModel.setBarcode(value)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            readOnly = !(productName.isEmpty() || productName.isBlank()),
                            textStyle = TextStyle(
                                color = if (productSearchMode) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.primary
                            ),
                            keyboardActions = KeyboardActions(
                                onSearch = {
                                    rootViewModel.searchProductByQrCode(value = barcode)
                                    hideKeyboard()
                                }
                            ),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Search,
                                capitalization = KeyboardCapitalization.Characters
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
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_barcode_scanner_24),
                            contentDescription = null,
                            tint = OrangeColor,
                            modifier = Modifier.clickable {
                                rootViewModel.setProductSearchMode(true)
                                rootViewModel.resetSelectedProduct()
                                onProductNameError()
                                onScanButtonClicked(ScanFrom.PURCHASE_SCREEN)
                            }
                        )
                    },
                   // colors = OutlinedTextFieldDefaults.colors(),
                    contentPadding = OutlinedTextFieldDefaults.contentPadding(
                        start = 8.dp,
                        top = 8.dp,
                        end = 8.dp,
                        bottom = 8.dp,
                    ),
                    container = {
                       // OutlinedTextFieldDefaults.ContainerBox(enabled, isError, interactionSource, colors)
                        OutlinedTextFieldDefaults.ContainerBox(
                            enabled = true,
                            isError = false,
                            interactionSource = interactionSource,
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color.Black.copy(alpha = 0.4f),
                                focusedBorderColor = Color.Blue,
                                disabledBorderColor = Color.Black,
                            ),
                            focusedBorderThickness = 0.5.dp,
                            shape = RoundedCornerShape(20)
                        )
                    },
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
                OutlinedTextFieldDefaults.DecorationBox(
                    value = qty,
                    innerTextField = {
                        BasicTextField(
                            value = qty,
                            onValueChange = { value ->
                                onQuantityError()
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
                                color = if (productSearchMode) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.primary
                            ),
                            readOnly = !(productName.isNotEmpty() && productName.isNotBlank())
                        )
                    },
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                    isError = showQuantityError,
                    label = {
                        Text(
                            text = "Qty",
                            fontSize = 10.sp,
                            textAlign = if (qty.isEmpty()) TextAlign.Center else TextAlign.Start,
                            modifier = if (qty.isEmpty()) Modifier.fillMaxWidth() else Modifier
                        )
                    },
                    placeholder = {
                        Text(text = "1")
                    },
                    colors = OutlinedTextFieldDefaults.colors(),
                    contentPadding = OutlinedTextFieldDefaults.contentPadding(
                        start = if (qty.isEmpty()) 8.dp else 2.dp,
                        top = 8.dp,
                        end = if (qty.isEmpty()) 8.dp else 2.dp,
                        bottom = 8.dp,
                    ),
                    container = {
                      //  OutlinedTextFieldDefaults.ContainerBox(enabled, isError, interactionSource, colors)
                        OutlinedTextFieldDefaults.ContainerBox(
                            enabled = true,
                            isError = false,
                            interactionSource = interactionSource,
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color.Black.copy(alpha = 0.4f),
                                focusedBorderColor = Color.Blue,
                                disabledBorderColor = Color.Black,
                            ),
                            focusedBorderThickness = 0.5.dp,
                            shape = RoundedCornerShape(20)
                        )
                    },
                )
            }

            // Product Unit box
            Box(
                modifier = Modifier
                    .weight(1.1f)
                    .padding(
                        start = 2.dp, end = 2.dp
                    )
            ) {
                OutlinedTextFieldDefaults.DecorationBox(
                    value = unit,
                    innerTextField = {
                        val value = if (unit.length > 3) {
                            unit.take(3)
                        } else {
                            unit
                        }
                        BasicTextField(
                            value = value,
                            onValueChange = {},
                            textStyle = TextStyle(
                                textAlign = TextAlign.Center,
                                color = if (productSearchMode) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.primary
                            ),
                            readOnly = true
                        )

                        /* Row(
                             verticalAlignment = Alignment.CenterVertically,
                             horizontalArrangement = Arrangement.Center,
                             modifier = Modifier.fillMaxWidth()
                         ) {
                             val value = if (unit.length > 3) {
                                 unit.take(3)
                             } else {
                                 unit
                             }
                             Text(
                                 text = value,
                                 modifier = Modifier.weight(1f),
                                 //fontSize = 12.sp,
                                 color = if (productSearchMode) MaterialTheme.colors.onBackground else MaterialTheme.colors.primary
                             )
                             *//*Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = null,
                                tint = MaterialTheme.colors.OrangeColor,
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable(enabled = unitsList.isNotEmpty()) {
                                        *//**//* if (productName.isNotEmpty() || productName.isNotBlank()) {
                                            showDropDownMenu = true
                                        }*//**//*
                                    }
                            )*//*
                        }*/
                    },
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                    isError = unitsList.isEmpty(),
                    label = {
                        Text(
                            text = "Unit",
                            fontSize = 10.sp
                        )
                    },
                    placeholder = {
                        Text("PCs")
                    },
                    colors = OutlinedTextFieldDefaults.colors(),
                    contentPadding = OutlinedTextFieldDefaults.contentPadding(
                        start = 8.dp,
                        top = 8.dp,
                        end = 8.dp,
                        bottom = 8.dp,
                    ),
                    container = {
                        //OutlinedTextFieldDefaults.ContainerBox(enabled, isError, interactionSource, colors)
                        OutlinedTextFieldDefaults.ContainerBox(
                            enabled = true,
                            isError = false,
                            interactionSource = interactionSource,
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color.Black.copy(alpha = 0.4f),
                                focusedBorderColor = Color.Blue,
                                disabledBorderColor = Color.Black,
                            ),
                            focusedBorderThickness = 0.5.dp,
                            shape = RoundedCornerShape(20)
                        )
                    },
                )
                /*  DropdownMenu(
                      expanded = showDropDownMenu,
                      onDismissRequest = {
                          showDropDownMenu = false
                      },
                      modifier = Modifier.width(60.dp)
                  ) {
                      unitsList.forEach { value ->
                          DropdownMenuItem(
                              onClick = {
                                  rootViewModel.setUnit(value)
                                  showDropDownMenu = false
                              },
                              contentPadding = PaddingValues(all = 8.dp)
                          ) {
                              Text(text = value.unitName)
                          }
                      }
                  }*/
            }

            // Rate box
            Box(
                modifier = Modifier
                    .weight(1.45f)
                    .padding(
                        start = 2.dp, end = 2.dp
                    )
            ) {

                OutlinedTextFieldDefaults.DecorationBox(
                    value = rate,
                    innerTextField = {
                        BasicTextField(
                            value = rate,
                            onValueChange = { value ->
                                rootViewModel.setProductRate(value)
                            },
                            textStyle = TextStyle(
                                textAlign = TextAlign.Center,
                                color = if (productSearchMode) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.primary
                            ),
                            readOnly = !(productName.isNotEmpty() || productName.isNotBlank()),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Decimal
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    hideKeyboard()
                                }
                            )
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
                    placeholder = {
                        Text(text = "0.0")
                    },
                    colors = OutlinedTextFieldDefaults.colors(),
                    contentPadding = OutlinedTextFieldDefaults.contentPadding(
                        start = if (rate.isEmpty()) 8.dp else 2.dp,
                        top = 8.dp,
                        end = if (rate.isEmpty()) 8.dp else 2.dp,
                        bottom = 8.dp,
                    ),
                    container = {
                      //  OutlinedTextFieldDefaults.ContainerBox(enabled, isError, interactionSource, colors)
                        OutlinedTextFieldDefaults.ContainerBox(
                            enabled = true,
                            isError = false,
                            interactionSource = interactionSource,
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color.Black.copy(alpha = 0.4f),
                                focusedBorderColor = Color.Blue,
                                disabledBorderColor = Color.Black,
                            ),
                            focusedBorderThickness = 0.5.dp,
                            shape = RoundedCornerShape(20)
                        )
                    },
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


                OutlinedTextFieldDefaults.DecorationBox(
                    value = disc,
                    innerTextField = {
                        BasicTextField(
                            value = disc,
                            onValueChange = { value ->
                                rootViewModel.setDisc(value)
                            },
                            textStyle = TextStyle(
                                textAlign = TextAlign.Center,
                                color = if (productSearchMode) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.primary
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
                            readOnly = !(productName.isNotEmpty() && productName.isNotBlank())
                        )
                    },
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                    label = {
                        Text(
                            text = "Disc",
                            fontSize = 10.sp
                        )
                    },
                    placeholder = {
                        Text(
                            text = "0.0",
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(),
                    contentPadding = OutlinedTextFieldDefaults.contentPadding(
                        start = if (disc.isEmpty()) 8.dp else 2.dp,
                        top = 8.dp,
                        end = if (disc.isEmpty()) 8.dp else 2.dp,
                        bottom = 8.dp,
                    ),
                    container = {
                       // OutlinedTextFieldDefaults.ContainerBox(enabled, isError, interactionSource, colors)
                        OutlinedTextFieldDefaults.ContainerBox(
                            enabled = true,
                            isError = false,
                            interactionSource = interactionSource,
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color.Black.copy(alpha = 0.4f),
                                focusedBorderColor = Color.Blue,
                                disabledBorderColor = Color.Black,
                            ),
                            focusedBorderThickness = 0.5.dp,
                            shape = RoundedCornerShape(20)
                        )
                    },
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
                OutlinedTextFieldDefaults.DecorationBox(
                    value = tax,
                    innerTextField = {
                        BasicTextField(
                            value = tax,
                            onValueChange = {

                            },
                            textStyle = TextStyle(
                                textAlign = TextAlign.Center,
                                color = if (productSearchMode) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.primary
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
                    placeholder = {
                        Text(
                            text = "NA",
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(),
                    contentPadding = OutlinedTextFieldDefaults.contentPadding(
                        start = if (tax.isEmpty()) 8.dp else 2.dp,
                        top = 8.dp,
                        end = if (tax.isEmpty()) 8.dp else 2.dp,
                        bottom = 8.dp,
                    ),
                    container = {
                        //OutlinedTextFieldDefaults.ContainerBox(enabled, isError, interactionSource, colors)
                        OutlinedTextFieldDefaults.ContainerBox(
                            enabled = true,
                            isError = false,
                            interactionSource = interactionSource,
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color.Black.copy(alpha = 0.4f),
                                focusedBorderColor = Color.Blue,
                                disabledBorderColor = Color.Black,
                            ),
                            focusedBorderThickness = 0.5.dp,
                            shape = RoundedCornerShape(20)
                        )
                    },
                )
            }


            // net amount box read only
            Box(
                modifier = Modifier
                    .weight(1.45f)
                    .padding(
                        start = 2.dp
                    )
            ) {
                roundOff = ((net * 100f).roundToInt()) / 100f
                OutlinedTextFieldDefaults.DecorationBox(
                    value = roundOff.toString(),
                    innerTextField = {
                        BasicTextField(
                            value = roundOff.toString(),
                            onValueChange = {
                            },
                            textStyle = TextStyle(
                                textAlign = TextAlign.Center,
                                color = OrangeColor
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
                            color = OrangeColor,
                        )
                    },
                    placeholder = {
                        Text(
                            text = "0.0",
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(),
                    contentPadding = OutlinedTextFieldDefaults.contentPadding(
                        start = 8.dp,
                        top = if (rate.isEmpty()) 9.dp else 8.dp,
                        end = 8.dp,
                        bottom = if (rate.isEmpty()) 9.dp else 8.dp,
                    ),
                    container = {
                       // OutlinedTextFieldDefaults.ContainerBox(enabled, isError, interactionSource, colors)
                        OutlinedTextFieldDefaults.ContainerBox(
                            enabled = true,
                            isError = false,
                            interactionSource = interactionSource,
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color.Black.copy(alpha = 0.4f),
                                focusedBorderColor = Color.Blue,
                                disabledBorderColor = Color.Black,
                            ),
                            focusedBorderThickness = 0.5.dp,
                            shape = RoundedCornerShape(20)
                        )
                    },
                )
            }
        }
    }
}

/*@Composable
fun AutoResizedText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = MaterialTheme.typography.body1,
    color: Color = style.color
) {
    var resizedTextStyle by remember {
        mutableStateOf(style)
    }

    var shouldDraw by remember {
        mutableStateOf(false)
    }

    val defaultFontSize = 12.sp

    Text(
        text = text,
        color = color,
        modifier = modifier.drawWithContent {
            if (shouldDraw) {
                drawContent()
            }
        },
        softWrap = false,
        style = resizedTextStyle,
        onTextLayout = { result ->

            if (result.didOverflowWidth) {
                if (style.fontSize.isUnspecified) {
                    resizedTextStyle = resizedTextStyle.copy(
                        fontSize = defaultFontSize
                    )
                }
                resizedTextStyle = resizedTextStyle.copy(
                    fontSize = resizedTextStyle.fontSize * 0.95
                )
            } else {
                shouldDraw = true
            }
        }
    )
}*/


