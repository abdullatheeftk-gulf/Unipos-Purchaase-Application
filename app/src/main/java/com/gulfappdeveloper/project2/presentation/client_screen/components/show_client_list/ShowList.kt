package com.gulfappdeveloper.project2.presentation.client_screen.components.show_client_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.ui.theme.*

@Composable
fun ShowList(
    rootViewModel: RootViewModel,
    navHostController: NavHostController
) {
    val clientList = rootViewModel.clientDetailsList

    LazyColumn {
        itemsIndexed(clientList) { index, client ->
            val categoryColor = when (index % 5) {
                0 -> {
                    MaterialTheme.colors.Color1
                }
                1 -> {
                    MaterialTheme.colors.Color2
                }
                2 -> {
                    MaterialTheme.colors.Color3
                }
                3 -> {
                    MaterialTheme.colors.Color4
                }
                4 -> {
                    MaterialTheme.colors.Color5
                }
                else -> {
                    MaterialTheme.colors.primary
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 6.dp)
                    .clickable {
                        rootViewModel.setClientDetails(client)
                        rootViewModel.setClientSearchText("")
                        navHostController.popBackStack()
                    },
                shape = RoundedCornerShape(35),
                border = BorderStroke(width = 1.dp, color = categoryColor)
            ) {
                Text(
                    text = client.clientName,
                    modifier = Modifier.padding(all = 10.dp),
                )
            }
        }
    }
}