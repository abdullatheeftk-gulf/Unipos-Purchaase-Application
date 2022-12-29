package com.gulfappdeveloper.project2.presentation.product_list_screen.components.product_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import com.gulfappdeveloper.project2.ui.theme.*

@Composable
fun ShowProductList(
    rootViewModel: RootViewModel,
) {
    val productList = rootViewModel.productList

    LazyColumn {
        itemsIndexed(productList) { index, product ->
            val categoryColor = when (index % 5) {
                0 -> {
                    MaterialTheme.colors.Color1
                }
                1 -> {
                    MaterialTheme.colors.Color2
                }
                2 -> {
                    MaterialTheme.colors.Color3
                }
                3 -> {
                    MaterialTheme.colors.Color4
                }
                4 -> {
                    MaterialTheme.colors.Color5
                }
                else -> {
                    MaterialTheme.colors.primary
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 6.dp)
                    .clickable {
                        rootViewModel.setProductListEvent(UiEvent.Navigate(""))
                        rootViewModel.setProductSearchMode(false)
                        rootViewModel.setProductSearchText(product.productName)
                        rootViewModel.setSelectedProduct(product)
                    },
                shape = RoundedCornerShape(35),
                border = BorderStroke(width = 1.dp, color = categoryColor)
            ) {
                Text(
                    text = product.productName,
                    modifier = Modifier.padding(all = 10.dp)
                )
            }
        }
    }
}