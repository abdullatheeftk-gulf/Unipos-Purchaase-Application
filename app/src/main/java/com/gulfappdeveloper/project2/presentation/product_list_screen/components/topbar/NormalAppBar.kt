package com.gulfappdeveloper.project2.presentation.product_list_screen.components.topbar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable

@Composable
fun NormalAppBar(
    onBackButtonPressed: () -> Unit,
    onSearchButtonPressed: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "ProductList")
        },
        navigationIcon = {
            IconButton(onClick = onBackButtonPressed) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onClick = onSearchButtonPressed) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null
                )
            }
        }
    )
}