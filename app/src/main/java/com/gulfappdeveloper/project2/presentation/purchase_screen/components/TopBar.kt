package com.gulfappdeveloper.project2.presentation.purchase_screen.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.ui.theme.OrangeColor

@Composable
fun TopBar(
    navHostController: NavHostController,
    rootViewModel: RootViewModel
) {
    var expanded by remember {
        mutableStateOf(false)
    }

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
        backgroundColor = Color.White,
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
            )) {
                DropdownMenuItem(onClick = {
                    expanded = false
                    rootViewModel.saveProductPurchaseSessionToRoom()
                }) {
                    Text(text = "Save this Session")
                }
                DropdownMenuItem(onClick = {
                    expanded = false
                    rootViewModel.loadProductPurchaseSessionFromRoom()
                }
                ) {
                    Text(text = "Load Data from the saved session")
                }
               /* DropdownMenuItem(onClick = {
                    expanded = false
                }
                ) {
                    Text(text = "Delete saved session")
                }*/
            }
        }
    )
}