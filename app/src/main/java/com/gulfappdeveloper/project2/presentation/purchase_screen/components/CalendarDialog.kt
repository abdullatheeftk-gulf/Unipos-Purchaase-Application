package com.gulfappdeveloper.project2.presentation.purchase_screen.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.gulfappdeveloper.project2.databinding.CalendarBinding
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import java.text.SimpleDateFormat
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarDialog(
    onDismissRequest: () -> Unit,
    rootViewModel: RootViewModel

) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
    )
    {
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
                    rootViewModel.setDate(date!!)
                    onDismissRequest()
                }
            }
        }
    }
}