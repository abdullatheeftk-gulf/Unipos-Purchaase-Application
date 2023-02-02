package com.gulfappdeveloper.project2.presentation.add_product_main_screen.presentation.add_product_home_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.domain.models.remote.post.add_product.ProductUnit
import com.gulfappdeveloper.project2.presentation.add_product_main_screen.AddProductMainViewModel
import com.gulfappdeveloper.project2.ui.theme.OrangeColor

@Composable
fun MultiUnitListTile(
    item: ProductUnit,
    index:Int,
    addProductMainViewModel: AddProductMainViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (item.productUnitName.length>20) 45.dp else 30.dp),
        verticalAlignment = Alignment.Top
    ) {

        Text(
            text = item.productUnitName,
            textAlign = TextAlign.Start,
            fontSize = 12.sp,
            modifier = Modifier.weight(5f),
            color = MaterialTheme.colors.primary,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = item.barcode,
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            modifier = Modifier.weight(2.5f),
            color = MaterialTheme.colors.primary
        )
        Text(
            text = item.unitQty.toString(),
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            modifier = Modifier.weight(1.5f),
            color = MaterialTheme.colors.primary
        )
        Text(
            text = item.salesPrice.toString(),
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            modifier = Modifier.weight(2f),
            maxLines = 1,
            color = MaterialTheme.colors.primary
        )
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            tint = MaterialTheme.colors.OrangeColor,
            modifier = Modifier
                .width(48.dp)
                .align(Alignment.Top)
                .clickable {
                   addProductMainViewModel.deleteOneItemFromMultiUnitList(index)
                }
        )


    }
}