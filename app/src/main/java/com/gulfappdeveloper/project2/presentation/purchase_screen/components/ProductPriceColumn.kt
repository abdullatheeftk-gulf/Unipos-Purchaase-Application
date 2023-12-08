package com.gulfappdeveloper.project2.presentation.purchase_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import kotlin.math.roundToInt

@Composable
fun ProductPriceColumn(
    rootViewModel: RootViewModel
) {
    val subTotal by rootViewModel.subTotal
    val discount by rootViewModel.totalDiscount
    val vat by rootViewModel.totalVat
    val grandTotal by rootViewModel.grandTotal

    var roundOff:Float
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        roundOff = (subTotal*100f).roundToInt()/100f

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
        ) {
            Text(
                text = "Sub Total",
                modifier = Modifier.weight(2f)
            )
            Text(
                text = ":",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Text(
                text = roundOff.toString(),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
        roundOff = (discount*100f).roundToInt()/100f
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
        ) {
            Text(
                text = "Discount Amount",
                modifier = Modifier.weight(2f)
            )
            Text(
                text = ":",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Text(
                text = roundOff.toString(),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
        roundOff = (vat*100f).roundToInt()/100f
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
        ) {
            Text(
                text = "Vat Amount",
                modifier = Modifier.weight(2f)
            )
            Text(
                text = ":",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Text(
                text = roundOff.toString(),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
        ) {
            Text(
                text = "Grand Total",
                modifier = Modifier.weight(2f)
            )
            Text(
                text = ":",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            roundOff = (grandTotal*100f).roundToInt()/100f
            Text(
                text = roundOff.toString(),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }


    }
}

