package com.gulfappdeveloper.project2.presentation.splash_screen.componenets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.presentation.ui_util.screenSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoLicenseAlertDialog(
    onDismissRequest: () -> Unit,
) {
    val screenWidth = screenSize().value
    AlertDialog(
        onDismissRequest = onDismissRequest,)
        {
        Card(elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "No License information on your mobile, Please Activate the App",
                    fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
                    textAlign = TextAlign.Center,
                    fontSize = if(screenWidth<600f) 14.sp else if(screenWidth>=600f && screenWidth<900f) 18.sp else 22.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismissRequest) {
                        Text(
                            text = "Ok",
                            fontSize = if(screenWidth<600f) 14.sp else if(screenWidth>=600 && screenWidth<900) 18.sp else 22.sp
                        )
                    }
                }

            }
        }

        }
}