package com.gulfappdeveloper.project2.presentation.purchase_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ProductListTitle() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Si",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                modifier = Modifier.weight(1f),
               // textDecoration = TextDecoration.Underline
            )
            Text(
                text = "Product Item",
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                modifier = Modifier.weight(5.5f),
                //textDecoration = TextDecoration.Underline
            )
            Text(
                text = "Qty",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                modifier = Modifier.weight(1f),
               // textDecoration = TextDecoration.Underline
            )
            Text(
                text = "Unit",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                modifier = Modifier.weight(1.4f),
                textDecoration = TextDecoration.Underline,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Rate",
                textAlign = TextAlign.Center,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(2f),
               // textDecoration = TextDecoration.Underline
            )
            Text(
                text = "Dis",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                modifier = Modifier.weight(1.5f),
              //  textDecoration = TextDecoration.Underline
            )
            Text(
                text = "Vat%",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 9.sp,
                modifier = Modifier.weight(1f),
              //  textDecoration = TextDecoration.Underline
            )
            Text(
                text = "Net",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                modifier = Modifier.weight(2.5f),
               // textDecoration = TextDecoration.Underline
            )
            Text(
                text = "",
                textAlign = TextAlign.Center,
                fontSize = 11.sp,
                modifier = Modifier.weight(0.85f)
            )

        }

       /* Divider(
            color = Color.Black,
            thickness = Dp.Hairline,
            modifier = Modifier.padding(horizontal = 2.dp),
            startIndent = 0.dp
        )*/

    }

}

/*@Preview
@Composable
fun Test() {
    ProductListTitle()
}*/


