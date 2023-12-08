package com.gulfappdeveloper.project2.presentation.uni_licence_act_screen.components

import android.util.Log
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.domain.datastore.UniLicenseDetails
import com.gulfappdeveloper.project2.ui.theme.OrangeColor
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LicenseInformationDisplayAlertDialog(
    onDismissRequest: () -> Unit,
    onLicenseExpired:()->Unit,
    uniLicense:UniLicenseDetails?
) {


    val expDateString = uniLicense?.expiryDate
    expDateString?.let {
        if (it.isNotEmpty() || it.isNotBlank()) {
            if (isUniposLicenseExpired(it)) {
                AlertDialog(
                    onDismissRequest = onLicenseExpired,
                )
                {
                    Card(elevation = CardDefaults.cardElevation(6.dp)) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(16.dp)
                        ) {


                            Text(
                                text = "Expired License",
                                color = MaterialTheme.colorScheme.error,
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                                fontStyle = MaterialTheme.typography.headlineMedium.fontStyle
                            )

                            Button(
                                onClick = onLicenseExpired,
                                modifier = Modifier.align(Alignment.End)
                            ) {
                                Text(text = "OK")
                            }
                        }
                    }

                }

                return
            }
        }
    }

    AlertDialog(
        onDismissRequest = onDismissRequest,
    )
    {
        Card (elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(10.dp)
            )
            {
                Text(
                    text = "Unipos License Information",
                    fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
                    fontSize = 22.sp,
                    textDecoration = TextDecoration.Underline,
                    color = MaterialTheme.colorScheme.primary
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
                        color = if (uniLicense?.licenseType == "demo") OrangeColor else MaterialTheme.colorScheme.primary,
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
                        color = if (uniLicense?.licenseType == "demo") MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Button(onClick = onDismissRequest) {
                    Text(text = "OK")
                }

            }
        }

    }

}

private fun isUniposLicenseExpired(eDate: String): Boolean {

    return try {
        val expDate: Date = SimpleDateFormat(
            "dd-MM-yyyy",
            Locale.getDefault()
        ).parse(eDate)!!

        val year = SimpleDateFormat("yyyy",Locale.getDefault()).format(Date())
        val month = SimpleDateFormat("MM",Locale.getDefault()).format(Date())
        val day = SimpleDateFormat("dd",Locale.getDefault()).format(Date())

        val currentDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).parse("${day}-${month}-${year}")

        val comparison = currentDate?.after(expDate)!!


        comparison
    } catch (e: Exception) {
        Log.e("Test", "isUniPosLicenseExpired: $e", )
        true
    }
}