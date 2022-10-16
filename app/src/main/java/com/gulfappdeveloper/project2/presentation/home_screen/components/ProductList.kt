package com.gulfappdeveloper.project2.presentation.home_screen.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProductList() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        contentPadding = PaddingValues(vertical = 4.dp)
    ) {
        item {
            Product()
        }
        item {
            Product()
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ProductListPrev() {
    ProductList()
}