package com.gulfappdeveloper.project2.presentation.purchase_screen.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.gulfappdeveloper.project2.databinding.CalendarBinding
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "CalendarDialog"

@Composable
fun CalendarDialog(
    onDismissRequest: () -> Unit,
    rootViewModel: RootViewModel

) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        buttons = {

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

        },
        shape = RoundedCornerShape(percent = 4)
    )
}