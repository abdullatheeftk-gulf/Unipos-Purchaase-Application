package com.gulfappdeveloper.project2.presentation.client_screen

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.client_screen.components.NormalTopBar
import com.gulfappdeveloper.project2.presentation.client_screen.components.SearchTopBar
import com.gulfappdeveloper.project2.presentation.client_screen.components.show_client_list.EmptyScreen
import com.gulfappdeveloper.project2.presentation.client_screen.components.show_client_list.ShowList
import com.gulfappdeveloper.project2.presentation.client_screen.util.ClientScreenUIEvent
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import kotlinx.coroutines.flow.collectLatest

private const val TAG = "ClientListScreen"
@Composable
fun ClientListScreen(
    rootViewModel: RootViewModel,
    navHostController: NavHostController,
    hideKeyboard: () -> Unit
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

    LaunchedEffect(key1 = true) {
        rootViewModel.clientScreenEvent.collectLatest { value: ClientScreenUIEvent ->
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
                    scaffoldState.snackbarHostState.showSnackbar(message = event.message)
                }
                else -> Unit
            }
        }
    }

    BackHandler(true) {
        Log.e(TAG, "ClientListScreen: $normalAndSearchTobBarToggle", )
        if (normalAndSearchTobBarToggle) {
            navHostController.popBackStack()
        } else {
            normalAndSearchTobBarToggle = true
            rootViewModel.getClientDetails()
            rootViewModel.setClientSearchText("")
        }
    }


    Scaffold(
        scaffoldState = scaffoldState,
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
            EmptyScreen()
        } else {
            ShowList(
                rootViewModel = rootViewModel,
                navHostController = navHostController
            )
        }


    }
}