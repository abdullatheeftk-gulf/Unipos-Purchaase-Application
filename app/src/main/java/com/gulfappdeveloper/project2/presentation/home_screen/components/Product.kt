package com.gulfappdeveloper.project2.presentation.home_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.domain.models.product_selected.ProductSelected

@Composable
fun Product(
    productSelected: ProductSelected,
    count:Int
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp),
            verticalAlignment = Alignment.Top
        ) {

            Text(
                text = (count+1).toString(),
                textAlign = TextAlign.Center,
                fontSize = 10.sp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = productSelected.productName,
                textAlign = TextAlign.Start,
                fontSize = 10.sp,
                maxLines = 2,
                modifier = Modifier.weight(6f),
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = productSelected.qty.toString(),
                textAlign = TextAlign.Center,
                fontSize = 10.sp,
                modifier = Modifier.weight(1.5f)
            )
            Text(
                text = productSelected.unit,
                textAlign = TextAlign.Center,
                fontSize = 10.sp,
                modifier = Modifier.weight(1.5f)
            )
            Text(
                text = productSelected.productRate.toString(),
                textAlign = TextAlign.Center,
                fontSize = 10.sp,
                modifier = Modifier.weight(1.5f)
            )
            Text(
                text = productSelected.disc.toString(),
                textAlign = TextAlign.Center,
                fontSize = 10.sp,
                modifier = Modifier.weight(1.5f)
            )
            Text(
                text = productSelected.vat.toString(),
                textAlign = TextAlign.Center,
                fontSize = 10.sp,
                modifier = Modifier.weight(1.5f)
            )
            Text(
                text = productSelected.net.toString(),
                textAlign = TextAlign.Center,
                fontSize = 10.sp,
                modifier = Modifier.weight(1.5f)
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = MaterialTheme.colors.error,
                modifier = Modifier
                    .height(18.dp)
                    .weight(1f)
            )

        }
        Divider(
            color = Color.LightGray,
            thickness = Dp.Hairline,
            modifier = Modifier.padding(horizontal = 14.dp),
            startIndent = 0.dp
        )
    }


}


