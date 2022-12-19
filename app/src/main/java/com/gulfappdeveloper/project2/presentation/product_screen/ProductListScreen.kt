package com.gulfappdeveloper.project2.presentation.product_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.navigation.root.RootViewModel

@Composable
fun ProductListScreen(
    rootViewModel: RootViewModel,
    navHostController: NavHostController
) {

    val scaffoldState = rememberScaffoldState()

    val productList = rootViewModel.productDetailsList


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Products List")
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }

                }
            )
        }
    ) {
        it.calculateTopPadding()

        LazyColumn {
            items(productList) { product ->
                Card(modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        rootViewModel.setSelectedProduct(productDetails = product)
                        navHostController.popBackStack()
                    }) {
                    Text(text = product.productName, modifier = Modifier.padding(all = 10.dp))
                }
            }
        }

    }
}