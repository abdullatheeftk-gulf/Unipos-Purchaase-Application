package com.gulfappdeveloper.project2.presentation.home_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gulfappdeveloper.project2.navigation.root.RootViewModel

@Composable
fun ProductButtonRow(
    rootViewModel: RootViewModel
) {
    Row(
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        Button(
            onClick = {
                      rootViewModel.addToProductList()
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
        Button(
            onClick = { /*TODO*/ },
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
            Text(text = "Add to item")

        }
        Button(
            onClick = { /*TODO*/ },
            contentPadding = PaddingValues(
                horizontal = 2.dp
            ),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp)
        ) {
            Text(text = "More")
            Spacer(modifier = Modifier.width(2.dp))
            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
                tint = MaterialTheme.colors.error
            )

        }
    }
}

/*
@Preview
@Composable
fun ProductButtonRowPrev() {
    ProductButtonRow()
}*/
