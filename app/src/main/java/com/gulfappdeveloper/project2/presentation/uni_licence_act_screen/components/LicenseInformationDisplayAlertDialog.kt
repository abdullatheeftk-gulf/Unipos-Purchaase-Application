package com.gulfappdeveloper.project2.presentation.uni_licence_act_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.domain.datastore.UniLicenseDetails
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.ui.theme.OrangeColor
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun LicenseInformationDisplayAlertDialog(
    onDismissRequest: () -> Unit,
    onLicenseExpired:()->Unit,
    uniLicense:UniLicenseDetails?
) {


    val expDateString = uniLicense?.expiryDate
    expDateString?.let {
        if (it.isNotEmpty() || it.isNotBlank()) {
            if (isLicenseExpired(it)) {
                AlertDialog(
                    onDismissRequest = onLicenseExpired,
                    buttons = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(10.dp)
                        ) {


                            Text(
                                text = "Expired License",
                                color = MaterialTheme.colors.error
                            )

                            Button(
                                onClick = onLicenseExpired,
                                modifier = Modifier.align(Alignment.End)
                            ) {
                                Text(text = "OK")
                            }
                        }
                    }
                )

                return
            }
        }
    }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        shape = RoundedCornerShape(4),
        buttons = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(10.dp)
            )
            {
                Text(
                    text = "Unipos License Information",
                    fontStyle = MaterialTheme.typography.h6.fontStyle,
                    fontSize = 22.sp,
                    textDecoration = TextDecoration.Underline,
                    color = MaterialTheme.colors.primary
                )
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "License Type",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = ":-",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = if (uniLicense?.licenseType == "demo") "Demo" else "Permanent",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        color = if (uniLicense?.licenseType == "demo") MaterialTheme.colors.OrangeColor else MaterialTheme.colors.primary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Expiry Date",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = ":-",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = uniLicense?.expiryDate ?: "Nil",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        color = if (uniLicense?.licenseType == "demo") MaterialTheme.colors.error else MaterialTheme.colors.primary
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Button(onClick = onDismissRequest) {
                    Text(text = "OK")
                }

            }
        }
    )
}

private fun isLicenseExpired(eDate: String): Boolean {

    val expDate: Date = SimpleDateFormat(
        "dd-MM-yyyy",
        Locale.getDefault()
    ).parse(eDate)!!

    return expDate >= Date()
}