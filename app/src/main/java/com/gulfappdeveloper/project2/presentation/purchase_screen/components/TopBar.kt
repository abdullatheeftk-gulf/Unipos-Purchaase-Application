package com.gulfappdeveloper.project2.presentation.purchase_screen.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.ui.theme.OrangeColor

@Composable
fun TopBar(
    navHostController: NavHostController,
    rootViewModel: RootViewModel
) {

    TopAppBar(
        title = {
            Text(text = "Purchase Entry", color = MaterialTheme.colors.OrangeColor)
        },
        navigationIcon = {
            IconButton(onClick = {
                navHostController.popBackStack()
                rootViewModel.resetAll()

            }) {
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