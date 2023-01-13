package com.gulfappdeveloper.project2.presentation.home_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gulfappdeveloper.project2.navigation.root.RootViewModel

@Composable
fun SubmitAlertDialog(
    onDismissRequested: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequested,
        shape = RoundedCornerShape(10),
        buttons = {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = "Submitted successfully",
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontStyle = MaterialTheme.typography.h6.fontStyle,
                    color = MaterialTheme.colors.primary
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = onDismissRequested,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(text = "OK")
                }
            }
        }
    )

}