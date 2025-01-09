package com.gulfappdeveloper.project2.presentation.client_screen.components.show_client_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.navigation.root.RootViewModel

@Composable
fun ShowList(
    paddingValues: PaddingValues,
    rootViewModel: RootViewModel,
    navHostController: NavHostController
) {
    val clientList = rootViewModel.clientDetailsList

    LazyColumn (contentPadding = paddingValues){
        item { 
            Spacer(modifier = Modifier.height(4.dp))
        }
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
                border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.primary),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
            ) {
                Text(
                    text = client.clientName,
                    modifier = Modifier.padding(all = 10.dp),
                )
            }
        }
    }
}