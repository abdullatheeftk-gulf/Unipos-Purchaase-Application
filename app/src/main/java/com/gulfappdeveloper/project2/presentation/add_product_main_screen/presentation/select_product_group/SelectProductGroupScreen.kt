package com.gulfappdeveloper.project2.presentation.add_product_main_screen.presentation.select_product_group

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.presentation.add_product_main_screen.AddProductMainViewModel
import com.gulfappdeveloper.project2.presentation.add_product_main_screen.presentation.select_product_group.components.EmptyListScreen
import com.gulfappdeveloper.project2.presentation.add_product_main_screen.presentation.select_product_group.components.topbars.NormalTopBar
import com.gulfappdeveloper.project2.presentation.add_product_main_screen.presentation.select_product_group.components.topbars.SearchTopBar
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import com.gulfappdeveloper.project2.ui.theme.*
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SelectProductGroupScreen(
    hideKeyboard: () -> Unit,
    addProductMainViewModel: AddProductMainViewModel,
    addProductNavHostController: NavHostController,
) {
    val scaffoldState = rememberScaffoldState()

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
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
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
        scaffoldState = scaffoldState,
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
    ) {
        it.calculateTopPadding()
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

        LazyColumn {
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            itemsIndexed(productGroupList) { index, productGroup ->


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
                    border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.primary)
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