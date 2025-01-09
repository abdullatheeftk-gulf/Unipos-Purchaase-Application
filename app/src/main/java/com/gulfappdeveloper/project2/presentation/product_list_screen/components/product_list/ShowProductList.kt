package com.gulfappdeveloper.project2.presentation.product_list_screen.components.product_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.ui.theme.*


@Composable
fun ShowProductList(
    rootViewModel: RootViewModel,
    paddingValues: PaddingValues
) {
    val productList = rootViewModel.productList


    LazyColumn(contentPadding =paddingValues ) {
        item { Spacer(modifier = Modifier.height(4.dp)) }
        items(productList) {  product ->
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(35),
                onClick = {
                    rootViewModel.onProductListItemClicked(product = product)
                }
            ) {
                Text(
                    text = product.productName,
                    modifier = Modifier.padding(all = 10.dp)
                )
            }
        }
    }
}