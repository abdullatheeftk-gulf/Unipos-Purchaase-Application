package com.gulfappdeveloper.project2.presentation.price_adjustment_screens.showProductsForPriceAdjustmentScreens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent

@Composable
fun ShowSuccessAlertDialog(
    rootViewModel: RootViewModel,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        buttons = {
            Column(modifier = Modifier.padding(all = 12.dp)) {
                Text(
                    text = "Price adjusted Successfully",
                    color = MaterialTheme.colors.primary,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontStyle = MaterialTheme.typography.h6.fontStyle,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {
                        rootViewModel.sendShowProductsForPriceAdjustmentScreenEvent(UiEvent.Navigate(""))
                        onDismissRequest()
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(text = "OK")
                }
            }
        }
    )
}