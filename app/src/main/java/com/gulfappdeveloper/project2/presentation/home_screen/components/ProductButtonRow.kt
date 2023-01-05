package com.gulfappdeveloper.project2.presentation.home_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gulfappdeveloper.project2.navigation.root.RootViewModel

@Composable
fun ProductButtonRow(
    rootViewModel: RootViewModel,
    onProductAdded: () -> Unit,
    //onMoreButtonClicked: () -> Unit
) {
    var showDropDownMenu by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        Button(
            onClick = {
                rootViewModel.addToProductList()
                onProductAdded()
            },
            contentPadding = PaddingValues(
                horizontal = 2.dp
            ),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
            Text(text = "Add to list")
        }
   /*     Button(
            onClick = { *//*TODO*//* },
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp),
            contentPadding = PaddingValues(
                horizontal = 2.dp
            )
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
            Text(text = "Add to productItem")

        }*/
        Column(
            modifier = Modifier
                //.fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { showDropDownMenu = true },
                contentPadding = PaddingValues(horizontal = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "More")
                Spacer(modifier = Modifier.width(2.dp))
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = null,
                )
            }
            DropdownMenu(
                expanded = showDropDownMenu,
                onDismissRequest = {
                    showDropDownMenu = false
                }
            ) {
                DropdownMenuItem(
                    onClick = { showDropDownMenu = false }
                ) {
                    Button(
                        onClick = {
                            rootViewModel.resetSelectedProduct()
                            showDropDownMenu = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Clear Product")
                    }
                }
                DropdownMenuItem(
                    onClick = { showDropDownMenu = false }
                ) {
                    Button(onClick = { showDropDownMenu = false }) {
                        Text(text = "Clear Product List")
                    }
                }
            }

        }

    }
}


