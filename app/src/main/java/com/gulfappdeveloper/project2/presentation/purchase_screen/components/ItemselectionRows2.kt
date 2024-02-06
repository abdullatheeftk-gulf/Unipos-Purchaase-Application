package com.gulfappdeveloper.project2.presentation.purchase_screen.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.R
import com.gulfappdeveloper.project2.navigation.root.RootNavScreens
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.ui_util.ScanFrom
import com.gulfappdeveloper.project2.presentation.ui_util.screenSize
import com.gulfappdeveloper.project2.ui.theme.OrangeColor
import java.text.DecimalFormat
import kotlin.math.roundToInt

@Composable
fun ItemSelectionRows2(
    rootViewModel: RootViewModel,
    navHostController: NavHostController,
    hideKeyboard: () -> Unit,
    onScanButtonClicked: (ScanFrom) -> Unit,
    onProductNameError: () -> Unit,
    onQuantityError: () -> Unit,
    showProductNameError: Boolean,
    showQuantityError: Boolean,
) {
    val screenWidth = screenSize().value

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



    var roundOff: Float

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Product name
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
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
            readOnly = !productSearchMode,
            textStyle = TextStyle(
                color = if (productSearchMode) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.primary,
                fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    hideKeyboard()
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            label = {
                Text(
                    text = "Product Name",
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        onProductNameError()
                        rootViewModel.setProductSearchMode(true)
                        rootViewModel.resetSelectedProduct()
                        // navigation to AddProductScreen from the purchase screen
                        rootViewModel.setNavFrom(false)
                        navHostController.navigate(RootNavScreens.AddProductMainScreen.route)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = Color.Black,
                    )
                }
            },
            shape = MaterialTheme.shapes.medium,
            isError = showProductNameError,
        )

        Spacer(modifier = Modifier.height(
            if (screenWidth<600) 0.dp else if(screenWidth>=600 && screenWidth<800) 4.dp else 8.dp
        ))


        //Barcode row
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {


            // Qty
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 2.dp),
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
                    color = if (productSearchMode) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.primary,
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                ),
                readOnly = !(productName.isNotEmpty() && productName.isNotBlank()),
                enabled = true,
                singleLine = true,
                isError = showQuantityError,
                label = {
                    Text(
                        text = "Qty",
                        fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                    )
                },
                /*placeholder = {
                    Text(
                        text = "1",
                        fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                    )
                },*/
                shape = MaterialTheme.shapes.medium
            )

            // Unit
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 2.dp),
                value = unit,
                onValueChange = {},
                textStyle = TextStyle(
                    color = if (productSearchMode) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.primary,
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                ),
                readOnly = true,
                enabled = true,
                singleLine = true,
                isError = unitsList.isEmpty(),
                label = {
                    Text(
                        text = "Unit",
                        fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                    )
                },
                placeholder = {
                    Text(
                        "PCs",
                        fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                    )
                },
                shape = MaterialTheme.shapes.medium
            )



            // rate
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 2.dp),
                value = rate,
                onValueChange = { value ->
                    rootViewModel.setProductRate(value)
                },
                textStyle = TextStyle(
                    color = if (productSearchMode) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.primary,
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
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
                ),
                enabled = true,
                singleLine = true,
                label = {
                    Text(
                        text = "Rate",
                        fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                    )
                },
                placeholder = {
                    Text(
                        text = "0.0",
                        fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                    )
                },
                shape = MaterialTheme.shapes.medium
            )


            // Barcode
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f)
                    .padding(start = 2.dp),
                value = barcode,
                onValueChange = { value ->
                    rootViewModel.setBarcode(value)
                    if (value.endsWith("\n")) {
                        rootViewModel.searchProductByQrCode(value = barcode)
                        hideKeyboard()
                    }
                },
                readOnly = !(productName.isEmpty() || productName.isBlank()),
                textStyle = TextStyle(
                    color = if (productSearchMode) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.primary,
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                ),
                label = {
                    Text(
                        text = "Barcode",
                        fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                    )
                },
                keyboardActions = KeyboardActions(
                    onSearch = {
                        rootViewModel.searchProductByQrCode(value = barcode)
                        hideKeyboard()
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search,
                    capitalization = KeyboardCapitalization.Characters
                ),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            rootViewModel.setProductSearchMode(true)
                            rootViewModel.resetSelectedProduct()
                            onProductNameError()
                            onScanButtonClicked(ScanFrom.PURCHASE_SCREEN)
                        }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_barcode_scanner_24),
                            contentDescription = null,
                            tint = OrangeColor,
                        )
                    }
                },
                shape = MaterialTheme.shapes.medium
            )
        }

        Spacer(modifier = Modifier.height(
            if (screenWidth<600) 0.dp else if(screenWidth>=600 && screenWidth<800) 4.dp else 8.dp
        ))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Disc
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 2.dp),
                value = disc,
                onValueChange = { value ->
                    rootViewModel.setDisc(value)
                },
                textStyle = TextStyle(
                    color = if (productSearchMode) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.primary,
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
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
                readOnly = !(productName.isNotEmpty() && productName.isNotBlank()),
                enabled = true,
                singleLine = true,
                label = {
                    Text(
                        text = "Disc",
                        fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                    )
                },
                placeholder = {
                    Text(
                        text = "0.0",
                        fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                    )
                },
                shape = MaterialTheme.shapes.medium
            )

            // Tax
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 2.dp),
                value = tax,
                onValueChange = {},
                textStyle = TextStyle(
                    color = if (productSearchMode) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.primary,
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                ),
                readOnly = true,
                enabled = true,
                singleLine = true,
                label = {
                    Text(
                        text = if (tax.isEmpty()) "Tax" else "Tax %",
                        fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                    )
                },
                placeholder = {
                    Text(
                        text = "NA",
                        fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                    )
                },
                shape = MaterialTheme.shapes.medium
            )

            // Net
            roundOff = ((net * 100f).roundToInt()) / 100f
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
                    .padding(start = 2.dp),
                value = roundOff.toString(),
                onValueChange = {},
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    color = OrangeColor,
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                ),
                readOnly = true,
                enabled = true,
                singleLine = true,
                label = {
                    Text(
                        text = "Net",
                        fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                        color = OrangeColor,
                    )
                },
                placeholder = {
                    Text(
                        text = "0.0",
                        fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                    )
                },
                shape = MaterialTheme.shapes.medium
            )
        }
    }
}