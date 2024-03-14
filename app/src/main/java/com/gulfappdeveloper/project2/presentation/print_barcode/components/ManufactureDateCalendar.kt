package com.gulfappdeveloper.project2.presentation.print_barcode.components

import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.gulfappdeveloper.project2.databinding.CalendarBinding
import com.gulfappdeveloper.project2.presentation.print_barcode.PrintBarcodeScreenViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManufactureDateCalendar(
    onDismissRequest: () -> Unit,
    printBarcodeScreenViewModel: PrintBarcodeScreenViewModel
) {
    BasicAlertDialog(onDismissRequest = onDismissRequest) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            AndroidViewBinding(factory = (CalendarBinding::inflate)) {
                calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
                    val date = SimpleDateFormat(
                        "d/MM/yyyy",
                        Locale.getDefault()
                    ).parse("${dayOfMonth}/${month + 1}/${year}")
                    //printBarcodeScreenViewModel.setManuFactureDate(date)
                    onDismissRequest()
                }
            }
        }
    }
}