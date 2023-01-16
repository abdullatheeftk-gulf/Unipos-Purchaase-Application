package com.gulfappdeveloper.project2.presentation.stock_adjustment_screen.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun StockAdjustSubmittedAlertDialog(
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = "Stock Adjustment list submitted",
            )
        },
        confirmButton = {
            Button(onClick = onDismissRequest) {
                Text(text = "OK")
            }
        },
        shape = RoundedCornerShape(10)
    )
}


