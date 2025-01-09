package com.gulfappdeveloper.project2.presentation.client_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.client_screen.components.appbars.NormalTopBar
import com.gulfappdeveloper.project2.presentation.client_screen.components.appbars.SearchTopBar
import com.gulfappdeveloper.project2.presentation.client_screen.components.show_client_list.EmptyScreen
import com.gulfappdeveloper.project2.presentation.client_screen.components.show_client_list.ShowList
import com.gulfappdeveloper.project2.presentation.client_screen.util.ClientScreenEvent
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import kotlinx.coroutines.flow.collectLatest


@Composable
fun ClientListScreen(
    rootViewModel: RootViewModel,
    navHostController: NavHostController,
    hideKeyboard: () -> Unit
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

    LaunchedEffect(key1 = true) {
        rootViewModel.clientScreenEvent.collectLatest { value: ClientScreenEvent ->
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
                is UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(message = event.message)
                }
                else -> Unit
            }
        }
    }

    BackHandler(true) {
        if (normalAndSearchTobBarToggle) {
            navHostController.popBackStack()
        } else {
            normalAndSearchTobBarToggle = true
            rootViewModel.getClientDetails()
            rootViewModel.setClientSearchText("")
        }
    }


    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        topBar = {
            if (normalAndSearchTobBarToggle) {
                NormalTopBar(
                    onBackButtonClicked = { navHostController.popBackStack() },
                    onSearchButtonClicked = {
                        normalAndSearchTobBarToggle = false
                    }
                )
            } else {
                SearchTopBar(
                    rootViewModel = rootViewModel,
                    onClearButtonClicked = {
                        normalAndSearchTobBarToggle = true
                        rootViewModel.getClientDetails()
                        rootViewModel.setClientSearchText(value = "")
                    },
                    hideKeyboard = hideKeyboard
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
            EmptyScreen()
        } else {
            ShowList(
                paddingValues = paddingValues,
                rootViewModel = rootViewModel,
                navHostController = navHostController
            )
        }


    }
}