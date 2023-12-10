package com.gulfappdeveloper.project2.presentation.stock_adjustment_screen.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.presentation.ui_util.screenSize

@Composable
fun StockAdjustSubmittedAlertDialog(
    onDismissRequest: () -> Unit
) {
    val screenWidth = screenSize().value
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = "Stock Adjustment list submitted",
                fontSize = if(screenWidth<600f) 14.sp else if(screenWidth>=600 && screenWidth<800) 18.sp else 22.sp,
            )
        },
        confirmButton = {
            Button(onClick = onDismissRequest) {
                Text(
                    text = "OK",
                    fontSize = if(screenWidth<600f) 14.sp else if(screenWidth>=600 && screenWidth<800) 18.sp else 22.sp,
                )
            }
        },
        shape = RoundedCornerShape(10)
    )
}


