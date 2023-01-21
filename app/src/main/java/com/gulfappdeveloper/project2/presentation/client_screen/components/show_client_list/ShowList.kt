package com.gulfappdeveloper.project2.presentation.client_screen.components.show_client_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
        items(clientList) { client ->
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
                border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.primary)
            ) {
                Text(
                    text = client.clientName,
                    modifier = Modifier.padding(all = 10.dp),
                )
            }
        }
    }
}