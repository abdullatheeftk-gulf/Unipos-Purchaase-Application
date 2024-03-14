package com.gulfappdeveloper.project2.presentation.print_barcode.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun PrintBarcodeSuccessDialog(onDismissRequested:()->Unit) {
    
    AlertDialog(
        title = {
                Text(text = "Success")
        },
        text = {
               Text(text = "Printed Barcodes")
        },
        onDismissRequest = onDismissRequested,
        confirmButton = { 
            Button(onClick = onDismissRequested) {
                Text(text = "Ok")
            }
        }
    )
    
}