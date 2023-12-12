package com.gulfappdeveloper.project2.presentation.purchase_screen.components

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
fun SubmitAlertDialog(
    onDismissRequested: () -> Unit
) {
    val screenWidth = screenSize().value
    AlertDialog(
        onDismissRequest = onDismissRequested,
    )
    {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = "Submitted successfully",
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = onDismissRequested,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(
                        text = "OK",
                        fontSize = if(screenWidth<600) 14.sp else if(screenWidth>=600 && screenWidth<800) 18.sp else 22.sp
                    )
                }
            }
        }
    }
}