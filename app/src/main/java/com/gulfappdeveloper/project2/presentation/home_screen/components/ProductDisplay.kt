package com.gulfappdeveloper.project2.presentation.home_screen.components

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.domain.models.product_selected.ProductSelected
import com.gulfappdeveloper.project2.ui.theme.OrangeColor
import kotlin.math.roundToInt

private const val TAG = "ProductDisplay"

@Composable
fun ProductDisplay(
    productSelected: ProductSelected,
    count: Int,
    onItemClicked: (Int, ProductSelected) -> Unit
) {
    var roundOff = 0f

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
                color = MaterialTheme.colors.OrangeColor
            )
            Text(
                text = productSelected.productName,
                textAlign = TextAlign.Start,
                fontSize = 11.sp,
                maxLines = 2,
                modifier = Modifier.weight(5.5f),
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colors.primary
            )
            Text(
                text = productSelected.qty.toString(),
                textAlign = TextAlign.Center,
                fontSize = 11.sp,
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colors.primary
            )
            Text(
                text = productSelected.unit,
                textAlign = TextAlign.Center,
                fontSize = 11.sp,
                modifier = Modifier.weight(1.4f),
                color = MaterialTheme.colors.primary
            )
            roundOff = (productSelected.productRate * 100f).roundToInt() / 100f
            Text(
                text = roundOff.toString(),
                textAlign = TextAlign.Center,
                fontSize = 11.sp,
                modifier = Modifier.weight(2f),
                color = MaterialTheme.colors.primary
            )
            Text(
                text = productSelected.disc.toString(),
                textAlign = TextAlign.Center,
                fontSize = 11.sp,
                modifier = Modifier.weight(1.5f),
                color = MaterialTheme.colors.primary
            )
            Text(
                text = productSelected.vat.toString(),
                textAlign = TextAlign.Center,
                fontSize = 11.sp,
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colors.primary
            )
            roundOff = (productSelected.net * 100f).roundToInt() / 100f
            Text(
                text = roundOff.toString(),
                textAlign = TextAlign.Center,
                fontSize = 11.sp,
                modifier = Modifier.weight(2.5f),
                color = MaterialTheme.colors.primary
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = MaterialTheme.colors.OrangeColor,
                modifier = Modifier
                    .height(18.dp)
                    .weight(0.85f)
                    .clickable {
                        onItemClicked(count, productSelected)
                    }
            )

        }
        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 14.dp),
            startIndent = 0.dp
        )
    }


}


