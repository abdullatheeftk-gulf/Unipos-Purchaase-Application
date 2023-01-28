package com.gulfappdeveloper.project2.presentation.add_product_main_screen.presentation.multi_product_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.domain.models.remote.post.add_product.ProductUnit
import com.gulfappdeveloper.project2.presentation.add_product_main_screen.AddProductMainViewModel
import com.gulfappdeveloper.project2.ui.theme.OrangeColor

@Composable
fun ListArea(
    addProductMainViewModel: AddProductMainViewModel
) {

    val multiUnitList =  addProductMainViewModel.multiUnitProductList

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        border = BorderStroke(width = Dp.Hairline, color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled)),
       // shape = RectangleShape
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            verticalArrangement = Arrangement.Top,
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
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    modifier = Modifier.weight(5f),
                    textDecoration = TextDecoration.Underline
                )
                Text(
                    text = "Barcode",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    modifier = Modifier.weight(2.5f),
                    textDecoration = TextDecoration.Underline
                )
                Text(
                    text = "Qty",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    modifier = Modifier.weight(1.5f),
                    textDecoration = TextDecoration.Underline
                )
                Text(
                    text = "Price",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    modifier = Modifier.weight(2f),
                    textDecoration = TextDecoration.Underline,
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.width(48.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(contentPadding = PaddingValues(
                vertical = 0.dp
            )) {
                items(multiUnitList) { item ->
                  ListTile(item = item)
                }
            }
        }

    }

}

/*
@Preview
@Composable
fun ListAreaPrev() {
    ListArea()
}*/
