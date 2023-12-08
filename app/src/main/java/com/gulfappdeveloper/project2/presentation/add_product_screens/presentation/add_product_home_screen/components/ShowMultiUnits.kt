package com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.add_product_home_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.domain.models.remote.post.add_product.ProductUnit
import com.gulfappdeveloper.project2.presentation.add_product_screens.AddProductMainViewModel

@Composable
fun ShowMultiUnits(
    multiUnitList:SnapshotStateList<ProductUnit>,
    addProductMainViewModel: AddProductMainViewModel
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
        ,
        border = BorderStroke(width = Dp.Hairline, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Product Name",
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.weight(5f),
                    textDecoration = TextDecoration.Underline
                )
                Text(
                    text = "Barcode",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.weight(2.5f),
                    textDecoration = TextDecoration.Underline
                )
                Text(
                    text = "Qty",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1.5f),
                    textDecoration = TextDecoration.Underline
                )
                Text(
                    text = "Price",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.weight(2f),
                    textDecoration = TextDecoration.Underline,
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.width(24.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            multiUnitList.forEachIndexed {index,item->
               MultiUnitListTile(
                   item = item,
                   index = index,
                   addProductMainViewModel =addProductMainViewModel
               )
            }
        }

    }

}