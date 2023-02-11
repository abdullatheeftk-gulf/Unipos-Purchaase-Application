package com.gulfappdeveloper.project2.presentation.purchase_screen.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gulfappdeveloper.project2.domain.models.product_selected.ProductSelected
import com.gulfappdeveloper.project2.navigation.root.RootViewModel


@Composable
fun ProductList(
    rootViewModel: RootViewModel,
    onProductListItemClicked:(Int,ProductSelected)->Unit,
    lazyColumnState: LazyListState
) {
    val selectedProductList = rootViewModel.selectedProductList


    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 4.dp),
        state = lazyColumnState
    ) {
        items(selectedProductList.size) { count ->
            ProductDisplay(
                productSelected = selectedProductList[count],
                count = count,
                onItemClicked = onProductListItemClicked
            )
        }
    }
}
