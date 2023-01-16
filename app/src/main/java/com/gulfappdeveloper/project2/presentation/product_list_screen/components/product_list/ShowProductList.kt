package com.gulfappdeveloper.project2.presentation.product_list_screen.components.product_list

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.ui.theme.*

private const val TAG = "ShowProductList"

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShowProductList(
    rootViewModel: RootViewModel,
) {
    val productList = rootViewModel.productList

    //Log.i(TAG, "ShowProductList: ")

    LazyColumn {
        itemsIndexed(productList) { index, product ->
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colors.primary
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