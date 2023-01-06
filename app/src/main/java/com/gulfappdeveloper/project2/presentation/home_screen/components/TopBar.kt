package com.gulfappdeveloper.project2.presentation.home_screen.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.gulfappdeveloper.project2.ui.theme.OrangeColor

@Composable
fun TopBar() {

    TopAppBar(
        title = {
            Text(text = "Purchase Entry", color = MaterialTheme.colors.OrangeColor)
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    tint = MaterialTheme.colors.OrangeColor,
                    contentDescription = null
                )
            }
        },
        backgroundColor = Color.White
    )
}