package com.gulfappdeveloper.project2.presentation.purchase_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.domain.models.product_selected.ProductSelected
import com.gulfappdeveloper.project2.ui.theme.OrangeColor
import kotlin.math.roundToInt


@Composable
fun ProductDisplay(
    productSelected: ProductSelected,
    count: Int,
    onItemClicked: (Int, ProductSelected) -> Unit
) {
    var roundOff:Float

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp),
            verticalAlignment = Alignment.Top
        ) {

            Text(
                text = (count + 1).toString() + ",",
                textAlign = TextAlign.Center,
                fontSize = 11.sp,
                modifier = Modifier.weight(1f),
                color = OrangeColor
            )
            Text(
                text = productSelected.productName,
                textAlign = TextAlign.Start,
                fontSize = 11.sp,
                maxLines = 2,
                modifier = Modifier.weight(5.3f),
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = productSelected.qty.toString(),
                textAlign = TextAlign.Center,
                fontSize = 11.sp,
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = productSelected.unit,
                textAlign = TextAlign.Center,
                fontSize = 11.sp,
                modifier = Modifier.weight(1.4f),
                color = MaterialTheme.colorScheme.primary
            )
            roundOff = (productSelected.productRate * 100f).roundToInt() / 100f
            Text(
                text = roundOff.toString(),
                textAlign = TextAlign.Center,
                fontSize = 11.sp,
                modifier = Modifier.weight(2f),
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = productSelected.disc.toString(),
                textAlign = TextAlign.Center,
                fontSize = 11.sp,
                modifier = Modifier.weight(1.5f),
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = productSelected.vat.toString(),
                textAlign = TextAlign.Center,
                fontSize = 11.sp,
                modifier = Modifier.weight(1.3f),
                color = MaterialTheme.colorScheme.primary
            )
            roundOff = (productSelected.net * 100f).roundToInt() / 100f
            Text(
                text = roundOff.toString(),
                textAlign = TextAlign.Center,
                fontSize = 11.sp,
                modifier = Modifier.weight(2.5f),
                color = MaterialTheme.colorScheme.primary
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = OrangeColor,
                modifier = Modifier
                    .height(18.dp)
                    .weight(0.75f)
                    .clickable {
                        onItemClicked(count, productSelected)
                    }
            )

        }
        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 14.dp),
        )
    }


}


