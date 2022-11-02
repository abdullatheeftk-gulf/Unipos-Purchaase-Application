package com.gulfappdeveloper.project2.presentation.home_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ProductListTitle() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {

            Text(
                text = "SI",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Item",
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                modifier = Modifier.weight(6f)
            )
            Text(
                text = "Qty",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                modifier = Modifier.weight(1.5f)
            )
            Text(
                text = "Unit",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                modifier = Modifier.weight(1.5f)
            )
            Text(
                text = "Rate",
                textAlign = TextAlign.Center,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1.5f)
            )
            Text(
                text = "Dis",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                modifier = Modifier.weight(1.5f)
            )
            Text(
                text = "vat",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                modifier = Modifier.weight(1.5f)
            )
            Text(
                text = "Net",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                modifier = Modifier.weight(1.5f)
            )
            Text(
                text = "",
                textAlign = TextAlign.Center,
                fontSize = 10.sp,
                modifier = Modifier.weight(1f)
            )

        }

        Divider(
            color = Color.Black,
            thickness = Dp.Hairline,
            modifier = Modifier.padding(horizontal = 14.dp),
            startIndent = 0.dp
        )

    }

}

@Preview
@Composable
fun Test() {
    ProductListTitle()
}


