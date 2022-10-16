package com.gulfappdeveloper.project2.presentation.home_screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gulfappdeveloper.project2.R

@Composable
fun ItemSelectionRows() {

    var productItem by remember {
        mutableStateOf("")
    }
    var barcode by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = productItem,
                onValueChange = {

                },
                placeholder = {
                    Text(text = "Item")
                },
                modifier = Modifier
                    .weight(2f)
                    .padding(horizontal = 4.dp)
            )

            OutlinedTextField(
                value = barcode,
                onValueChange = {

                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_qr_code_scanner_24),
                        contentDescription = null,
                    )
                },
                placeholder = {
                    Text(text = "Barcode")
                },
                modifier = Modifier
                    .weight(1.2f)
                    .padding(horizontal = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))

        Row {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = 4.dp,
                        end = 1.dp
                    )
                    .border(
                        color = Color.LightGray,
                        width = 1.dp,
                        shape = MaterialTheme.shapes.medium
                    )
            ) {
                BasicTextField(
                    value = "Qty",
                    onValueChange = {

                    },
                    modifier = Modifier.padding(8.dp),
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center
                    )
                )
            }
            Box(
                modifier = Modifier
                    .weight(2f)
                    .padding(
                        start = 1.dp,
                        end = 1.dp
                    )
                    .border(
                        color = Color.LightGray,
                        width = 1.dp,
                        shape = MaterialTheme.shapes.medium
                    )
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BasicTextField(
                        value = "Pcs",
                        onValueChange = {

                        },
                        modifier = Modifier.weight(1f),
                        textStyle = TextStyle(
                            textAlign = TextAlign.Center
                        )

                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = MaterialTheme.colors.error,
                        modifier = Modifier
                            .size(15.dp)
                            .weight(1f),

                        )
                }

            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = 1.dp,
                        end = 1.dp
                    )
                    .border(
                        color = Color.LightGray,
                        width = 1.dp,
                        shape = MaterialTheme.shapes.medium
                    )
            ) {
                BasicTextField(
                    value = "Rate",
                    onValueChange = {

                    },
                    modifier = Modifier.padding(8.dp),
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center
                    )
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = 1.dp,
                        end = 1.dp
                    )
                    .border(
                        color = Color.LightGray,
                        width = 1.dp,
                        shape = MaterialTheme.shapes.medium
                    )
            ) {
                BasicTextField(
                    value = "Disc",
                    onValueChange = {

                    },
                    modifier = Modifier.padding(8.dp),
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center
                    )
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = 1.dp,
                        end = 1.dp
                    )
                    .border(
                        color = Color.LightGray,
                        width = 1.dp,
                        shape = MaterialTheme.shapes.medium
                    )
            ) {
                BasicTextField(
                    value = "Tax",
                    onValueChange = {

                    },
                    enabled = false,
                    modifier = Modifier.padding(8.dp),
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.error
                    )
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = 1.dp,
                        end = 4.dp
                    )
                    .border(
                        color = Color.LightGray,
                        width = 1.dp,
                        shape = MaterialTheme.shapes.medium
                    )
            ) {
                BasicTextField(
                    value = "Net",
                    onValueChange = {

                    },
                    enabled = false,
                    modifier = Modifier.padding(8.dp),
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.error
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemSelectionRowsPrev() {
    ItemSelectionRows()
}