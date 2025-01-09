package com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.add_product_home_screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.presentation.ui_util.screenSize

@Composable
fun OpeningStockBlock(
    openingStock: String,
    setOpeningStock: (String) -> Unit,
    hideKeyboard:()->Unit,

) {
    val screenWidth = screenSize().value
    Spacer(modifier = Modifier.height(20.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Opening Stock :- ",
            fontSize = if (screenWidth < 600) 18.sp else if (screenWidth >= 600 && screenWidth < 800) 22.sp else 26.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.weight(1f)
        )


        OutlinedTextField(
            value = openingStock,
            onValueChange = setOpeningStock,
            modifier = Modifier.weight(1.5f),
            label = {
                Text(
                    text = "Opening Stock",
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                )
            },
            placeholder = {
                Text(
                    text = "0.0",
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            ),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = MaterialTheme.colorScheme.primary,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    hideKeyboard()
                }
            ),
            textStyle = TextStyle(
                fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
            )
        )
    }
}