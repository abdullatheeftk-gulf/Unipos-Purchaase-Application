package com.gulfappdeveloper.project2.presentation.price_adjustment_screens.showProductsForPriceAdjustmentScreens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.R
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.ui_util.ScanFrom
import com.gulfappdeveloper.project2.ui.theme.OrangeColor

@Composable
fun PriceAdjustmentTopBar(
    rootViewModel: RootViewModel,
    onScanButtonClicked: (ScanFrom) -> Unit,
    onBackButtonClicked: () -> Unit,
    hideKeyboard: () -> Unit,
    onClearButtonClicked: () -> Unit
) {
    val productSearchText by rootViewModel.productNameForPriceAdjustment.value

    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.surface,
        shape = RectangleShape,
        elevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackButtonClicked) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colors.OrangeColor
                    )
                }
                BasicTextField(
                    value = productSearchText,
                    onValueChange = { value ->
                        rootViewModel.setProductNameSearchForPriceAdjustment(value)
                    },
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            rootViewModel.searchProductListByNameForPriceAdjustment()
                            hideKeyboard()
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search,
                        keyboardType = KeyboardType.Text
                    ),
                    decorationBox = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {

                            if (productSearchText.isEmpty()) {
                                Text(
                                    text = "Search",
                                    color = Color.DarkGray,
                                    modifier = Modifier
                                        .alpha(ContentAlpha.disabled)
                                )
                            }
                            it()
                        }

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .weight(1f)
                        .padding(all = 4.dp)
                        .clip(MaterialTheme.shapes.medium.copy(all = CornerSize(16.dp)))
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colors.primary,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .background(color = Color.White),
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                    ),
                    singleLine = true

                )

                IconButton(onClick = {
                    onClearButtonClicked()
                    hideKeyboard()
                }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = null,
                        tint = MaterialTheme.colors.error
                    )
                }

                IconButton(onClick = {
                    onScanButtonClicked(ScanFrom.PRICE_ADJUSTMENT_SCREEN)
                    onClearButtonClicked()
                    hideKeyboard()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_barcode_scanner_24),
                        contentDescription = null,
                        tint = MaterialTheme.colors.OrangeColor,
                    )
                }
            }

            if (productSearchText.isNotEmpty() || productSearchText.isNotBlank()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            hideKeyboard()
                            rootViewModel.searchProductListByNameForPriceAdjustment()
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.OrangeColor
                        )
                    ) {
                        Text(text = "Search by name", color = Color.Black)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Button(
                        onClick = {
                            hideKeyboard()
                            rootViewModel.searchProductByQrCodeForPriceAdjustment(productSearchText)
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.OrangeColor
                        )
                    ) {
                        Text(text = "Search by barcode", color = Color.Black)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }


    }
}