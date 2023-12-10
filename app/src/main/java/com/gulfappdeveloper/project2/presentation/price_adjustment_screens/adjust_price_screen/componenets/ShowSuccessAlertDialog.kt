package com.gulfappdeveloper.project2.presentation.price_adjustment_screens.adjust_price_screen.componenets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.presentation.ui_util.screenSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowSuccessAlertDialog(
    onDismissRequest: () -> Unit
) {
    val screenWidth = screenSize().value
    AlertDialog(
        onDismissRequest = onDismissRequest
    ) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(modifier = Modifier.padding(all = 12.dp)) {
                Text(
                    text = "Price adjusted successfully",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {
                        onDismissRequest()
                    }, modifier = Modifier.align(Alignment.End)
                ) {
                    Text(
                        text = "OK",
                        fontSize = if (screenWidth < 600f) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                    )
                }
            }
        }

    }

}