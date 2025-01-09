package com.gulfappdeveloper.project2.presentation.client_screen.components.appbars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.gulfappdeveloper.project2.ui.theme.OrangeColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NormalTopBar(
    onBackButtonClicked: () -> Unit,
    onSearchButtonClicked: () -> Unit,
) {

    TopAppBar(
        modifier = Modifier.shadow(elevation = 6.dp),
        title = {
            Text(text = "Client Details", color = OrangeColor)
        },
        navigationIcon = {
            IconButton(onClick = onBackButtonClicked) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = OrangeColor
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