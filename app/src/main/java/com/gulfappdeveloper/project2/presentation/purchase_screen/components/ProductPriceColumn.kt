package com.gulfappdeveloper.project2.presentation.purchase_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.ui_util.screenSize
import com.gulfappdeveloper.project2.ui.theme.OrangeColor
import kotlin.math.roundToInt

@Composable
fun ProductPriceColumn(
    rootViewModel: RootViewModel
) {
    val screenWidth = screenSize().value
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
                modifier = Modifier.weight(2f),
                fontSize = if(screenWidth<600) 14.sp else if(screenWidth>=600 && screenWidth<800) 18.sp else 22.sp
            )
            Text(
                text = ":",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontSize = if(screenWidth<600) 14.sp else if(screenWidth>=600 && screenWidth<800) 18.sp else 22.sp
            )
            Text(
                text = roundOff.toString(),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontSize = if(screenWidth<600) 14.sp else if(screenWidth>=600 && screenWidth<800) 18.sp else 22.sp
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
                modifier = Modifier.weight(2f),
                fontSize = if(screenWidth<600) 14.sp else if(screenWidth>=600 && screenWidth<800) 18.sp else 22.sp
            )
            Text(
                text = ":",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontSize = if(screenWidth<600) 14.sp else if(screenWidth>=600 && screenWidth<800) 18.sp else 22.sp
            )
            Text(
                text = roundOff.toString(),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontSize = if(screenWidth<600) 14.sp else if(screenWidth>=600 && screenWidth<800) 18.sp else 22.sp
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
                modifier = Modifier.weight(2f),
                fontSize = if(screenWidth<600) 14.sp else if(screenWidth>=600 && screenWidth<800) 18.sp else 22.sp
            )
            Text(
                text = ":",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontSize = if(screenWidth<600) 14.sp else if(screenWidth>=600 && screenWidth<800) 18.sp else 22.sp
            )
            Text(
                text = roundOff.toString(),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontSize = if(screenWidth<600) 14.sp else if(screenWidth>=600 && screenWidth<800) 18.sp else 22.sp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
        ) {
            Text(
                text = "Grand Total",
                modifier = Modifier.weight(2f),
                fontSize = if(screenWidth<600) 18.sp else if(screenWidth>=600 && screenWidth<800) 22.sp else 26.sp
            )
            Text(
                text = ":",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontSize = if(screenWidth<600) 14.sp else if(screenWidth>=600 && screenWidth<800) 18.sp else 22.sp
            )
            roundOff = (grandTotal*100f).roundToInt()/100f
            Text(
                text = roundOff.toString(),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontSize = if(screenWidth<600) 18.sp else if(screenWidth>=600 && screenWidth<800) 22.sp else 26.sp,
                color = OrangeColor,
                fontWeight = FontWeight.Bold
            )
        }


    }
}

