package com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.multi_product_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.presentation.add_product_screens.AddProductMainViewModel
import com.gulfappdeveloper.project2.presentation.ui_util.screenSize

@Composable
fun ListArea(
    addProductMainViewModel: AddProductMainViewModel,
) {

    val screenWidth = screenSize().value

    val multiUnitList = addProductMainViewModel.multiUnitProductList

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        border = BorderStroke(
            width = Dp.Hairline,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
        ),
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
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 16.sp else 18.sp,
                    modifier = Modifier.weight(5f),
                    textDecoration = TextDecoration.Underline
                )
                Text(
                    text = "Barcode",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 16.sp else 18.sp,
                    modifier = Modifier.weight(2.5f),
                    textDecoration = TextDecoration.Underline
                )
                Text(
                    text = "Qty",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 16.sp else 18.sp,
                    modifier = Modifier.weight(1.5f),
                    textDecoration = TextDecoration.Underline
                )
                Text(
                    text = "Price",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 16.sp else 18.sp,
                    modifier = Modifier.weight(2f),
                    textDecoration = TextDecoration.Underline,
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.width(24.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                contentPadding = PaddingValues(
                    vertical = 0.dp
                )
            ) {
                itemsIndexed(multiUnitList) { index, item ->
                    ListTile(
                        item = item,
                        index = index,
                        addProductMainViewModel = addProductMainViewModel
                    )
                }
            }
        }

    }

}

