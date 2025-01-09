package com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.multi_product_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.domain.models.remote.post.add_product.ProductUnit
import com.gulfappdeveloper.project2.presentation.add_product_screens.AddProductMainViewModel
import com.gulfappdeveloper.project2.presentation.ui_util.screenSize
import com.gulfappdeveloper.project2.ui.theme.OrangeColor

@Composable
fun ListTile(
    item:ProductUnit,
    index:Int,
    addProductMainViewModel: AddProductMainViewModel
) {
    val screenWidth = screenSize().value
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (item.productUnitName.length > 20) 45.dp else 30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = item.productUnitName,
            textAlign = TextAlign.Start,
            fontSize = if (screenWidth < 600) 12.sp else if (screenWidth >= 600 && screenWidth < 800) 14.sp else 16.sp,
            modifier = Modifier.weight(5f),
            color = MaterialTheme.colorScheme.primary,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = item.barcode,
            textAlign = TextAlign.Center,
            fontSize = if (screenWidth < 600) 12.sp else if (screenWidth >= 600 && screenWidth < 800) 14.sp else 16.sp,
            modifier = Modifier.weight(2.5f),
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = item.unitQty.toString(),
            textAlign = TextAlign.Center,
            fontSize = if (screenWidth < 600) 12.sp else if (screenWidth >= 600 && screenWidth < 800) 14.sp else 16.sp,
            modifier = Modifier.weight(1.5f),
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = item.salesPrice.toString(),
            textAlign = TextAlign.Center,
            fontSize = if (screenWidth < 600) 12.sp else if (screenWidth >= 600 && screenWidth < 800) 14.sp else 16.sp,
            modifier = Modifier.weight(2f),
            maxLines = 1,
            color = MaterialTheme.colorScheme.primary
        )
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            tint = OrangeColor,
            modifier = Modifier
                .size(
                    if (screenWidth < 600) 24.dp else if (screenWidth >= 600 && screenWidth < 800) 30.dp else 36.dp,
                )
                .clickable {
                    addProductMainViewModel.deleteOneItemFromMultiUnitList(index)
                }
        )
    }

}

