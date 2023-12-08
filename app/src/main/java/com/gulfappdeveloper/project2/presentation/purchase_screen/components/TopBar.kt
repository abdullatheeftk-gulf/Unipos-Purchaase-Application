package com.gulfappdeveloper.project2.presentation.purchase_screen.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.ui.theme.OrangeColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navHostController: NavHostController,
    rootViewModel: RootViewModel
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    TopAppBar(
        modifier = Modifier.shadow(elevation = 6.dp),
        title = {
            Text(text = "Purchase Entry", color = OrangeColor)
        },
        navigationIcon = {
            IconButton(onClick = {
                navHostController.popBackStack()
                rootViewModel.resetAll()

            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    tint = OrangeColor,
                    contentDescription = null
                )
            }
        },
        // backgroundColor = Color.White,
        actions = {
            IconButton(
                onClick = {
                    expanded = true
                },
            ) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                offset = DpOffset(
                    0.dp,
                    (-5).dp,
                )
            ) {
                DropdownMenuItem(onClick = {
                    expanded = false
                    rootViewModel.saveProductPurchaseSessionToRoom()
                },
                    text = { Text(text = "Save this Session") })
                DropdownMenuItem(onClick = {
                    expanded = false
                    rootViewModel.loadProductPurchaseSessionFromRoom()
                },
                    text = { Text(text = "Load Data from the saved session") }
                )

            }
        }
    )
}