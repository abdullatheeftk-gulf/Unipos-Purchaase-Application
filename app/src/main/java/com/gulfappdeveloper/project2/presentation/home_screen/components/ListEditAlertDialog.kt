package com.gulfappdeveloper.project2.presentation.home_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.gulfappdeveloper.project2.domain.models.product_selected.ProductSelected
import com.gulfappdeveloper.project2.navigation.root.RootViewModel

@Composable
fun ListEditAlertDialog(
    rootViewModel: RootViewModel,
    productSelected: ProductSelected,
    count:Int,
    onDismissRequest: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        shape = RoundedCornerShape(8),
        buttons = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(all = 12.dp)
            ) {
                Text(
                    text = productSelected.productName,
                    textDecoration = TextDecoration.Underline,
                    textAlign = TextAlign.Center
                )
                TextButton(onClick = {
                    rootViewModel.setAProductForEditFromList(count = count,productSelected = productSelected)
                    onDismissRequest()
                }) {
                    Text(
                        text = "Edit"
                    )
                }
                TextButton(onClick = {
                    rootViewModel.deleteAProductFromSelectedProductList(count)
                    onDismissRequest()
                }) {
                    Text(
                        text = "Delete",
                        color = MaterialTheme.colors.error
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    )
}