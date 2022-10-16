package com.gulfappdeveloper.project2.presentation.home_screen.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun TopBar() {

    TopAppBar(
        title = {
            Text(text = "Purchase Entry", color = MaterialTheme.colors.error)
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    tint = MaterialTheme.colors.error,
                    contentDescription = null
                )
            }
        },
        backgroundColor = Color.White
    )
}