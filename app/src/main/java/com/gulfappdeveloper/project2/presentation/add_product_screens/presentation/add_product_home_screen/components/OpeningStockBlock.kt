package com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.add_product_home_screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OpeningStockBlock(
    openingStock: String,
    setOpeningStock: (String) -> Unit,
    hideKeyboard:()->Unit,

) {
    Spacer(modifier = Modifier.height(20.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Opening Stock :- ",
            fontSize = 18.sp,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.weight(1f)
        )


        OutlinedTextField(
            value = openingStock,
            onValueChange = setOpeningStock,
            modifier = Modifier.weight(1.5f),
            label = {
                Text(text = "Opening Stock")
            },
            placeholder = {
                Text(text = "0.0")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            ),
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.primary,
                backgroundColor = MaterialTheme.colors.surface
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    hideKeyboard()
                }
            )
        )
    }
}