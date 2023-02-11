package com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.select_product_group.components.topbars

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import com.gulfappdeveloper.project2.ui.theme.OrangeColor

@Composable
fun NormalTopBar(
    onBackButtonClicked: () -> Unit,
    onSearchButtonClicked: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(text = "Product Groups", color = MaterialTheme.colors.OrangeColor)
        },
        backgroundColor = MaterialTheme.colors.surface,
        navigationIcon = {
            IconButton(onClick = onBackButtonClicked) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colors.OrangeColor
                )
            }
        },
        actions = {
            IconButton(
                onClick = onSearchButtonClicked
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null
                )
            }
        }
    )
}