package com.gulfappdeveloper.project2.presentation.purchase_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.domain.models.product_selected.ProductSelected
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.ui_util.screenSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListEditAlertDialog(
    rootViewModel: RootViewModel,
    productSelected: ProductSelected,
    count: Int,
    onDismissRequest: () -> Unit,
) {
    val screenWidth = screenSize().value
    BasicAlertDialog(onDismissRequest = onDismissRequest) {
        Card(
            elevation = CardDefaults.cardElevation(6.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(all = 12.dp)
            ) {
                Text(
                    text = productSelected.productName,
                    textDecoration = TextDecoration.Underline,
                    textAlign = TextAlign.Center,
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row {

                    Button(
                        onClick = {
                            rootViewModel.deleteAProductFromSelectedProductList(count)
                            onDismissRequest()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error,
                            contentColor = MaterialTheme.colorScheme.surface
                        )
                    ) {
                        Text(
                            text = "Delete",
                            fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    Button(
                        onClick = {
                            rootViewModel.setAProductForEditFromList(
                                count = count,
                                productSelected = productSelected
                            )
                            onDismissRequest()
                        }, colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.surface
                        )
                    ) {
                        Text(
                            text = "Edit",
                            fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))
            }
        }

    }
}