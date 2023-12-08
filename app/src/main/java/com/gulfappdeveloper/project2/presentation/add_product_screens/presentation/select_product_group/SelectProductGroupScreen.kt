package com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.select_product_group

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.presentation.add_product_screens.AddProductMainViewModel
import com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.select_product_group.components.EmptyListScreen
import com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.select_product_group.components.topbars.NormalTopBar
import com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.select_product_group.components.topbars.SearchTopBar
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SelectProductGroupScreen(
    hideKeyboard: () -> Unit,
    addProductMainViewModel: AddProductMainViewModel,
    addProductNavHostController: NavHostController,
) {
    val snackBarHostState = remember {
        SnackbarHostState()
    }

    var normalAndSearchTobBarToggle by remember {
        mutableStateOf(true)
    }

    var showProgressBar by remember {
        mutableStateOf(false)
    }

    var showEmptyScreen by remember {
        mutableStateOf(false)
    }


    val productGroupList = addProductMainViewModel.productGroupsList

    LaunchedEffect(key1 = true) {
        addProductMainViewModel.selectedProductGroupEvent.collectLatest { value ->
            when (val event = value.uiEvent) {
                is UiEvent.ShowProgressBar -> {
                    showProgressBar = true
                }
                is UiEvent.CloseProgressBar -> {
                    showProgressBar = false
                }
                is UiEvent.ShowEmptyList -> {
                    showEmptyScreen = event.value
                }
                is UiEvent.Navigate -> {
                    addProductNavHostController.popBackStack()
                }
                is UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(event.message)
                }
                else -> Unit
            }
        }
    }

    BackHandler(true) {
        if (normalAndSearchTobBarToggle) {
            addProductNavHostController.popBackStack()
        } else {
            normalAndSearchTobBarToggle = true
            addProductMainViewModel.getProductGroups()
            addProductMainViewModel.setSearchText("")
        }
    }


    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        topBar = {
            if (normalAndSearchTobBarToggle) {
                NormalTopBar(
                    onBackButtonClicked = {
                        addProductNavHostController.popBackStack()
                    },
                    onSearchButtonClicked = {
                        normalAndSearchTobBarToggle = false
                    }
                )
            } else {
                SearchTopBar(
                    addProductViewModel = addProductMainViewModel,
                    onClearButtonClicked = {
                        normalAndSearchTobBarToggle = true
                        addProductMainViewModel.getProductGroups()
                        addProductMainViewModel.setSearchText("")
                    },
                    hideKeyboard = hideKeyboard,
                )
            }
        }
    ) {paddingValues->
        if (showProgressBar) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            return@Scaffold
        }
        if (showEmptyScreen) {
            EmptyListScreen()
            return@Scaffold
        }

        LazyColumn(contentPadding = paddingValues) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(productGroupList) {  productGroup ->


                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                        .clickable {

                            addProductMainViewModel.setSelectedProductGroups(productGroup = productGroup)
                            addProductMainViewModel.clearSearchText()

                            addProductNavHostController.popBackStack()
                        },
                    shape = RoundedCornerShape(35),
                    border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.primary)
                ) {
                    Text(
                        text = productGroup.pGroupName,
                        modifier = Modifier.padding(all = 10.dp),
                    )
                }
            }
        }


    }
}