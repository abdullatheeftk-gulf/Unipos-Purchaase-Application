package com.gulfappdeveloper.project2.presentation.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.navigation.root.RootNavScreens
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.home_screen.components.*
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import com.gulfappdeveloper.project2.presentation.ui_util.keyboardAsState
import kotlinx.coroutines.flow.collectLatest

private const val TAG = "HomeScreen"

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    hideKeyboard: () -> Unit,
    onScanButtonClicked: () -> Unit,
    rootViewModel: RootViewModel,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()


    val coroutineScope = rememberCoroutineScope()


    val listState = rememberLazyListState()

    val isKeyBoardOpen by keyboardAsState()

    var showCalendar by remember {
        mutableStateOf(false)
    }

    var showAddClientDialog by remember {
        mutableStateOf(false)
    }

    if (showAddClientDialog) {
        AddClientDialog(
            rootViewModel = rootViewModel,
            onDismissRequest = {
                showAddClientDialog = false
            }
        )
    }

    LaunchedEffect(key1 = true) {
        homeScreenViewModel.uiEvent.collectLatest { value ->
            when (value) {
                is UiEvent.AnimateWithKeyBoard -> {
                    // Log.d(TAG, "event: AnimateWithKeyBoard ")
                    listState.animateScrollToItem(2)
                }
                is UiEvent.AnimateBackWithKeyBoard -> {
                    // Log.e(TAG, "event: AnimateBackWithKeyBoard ")
                    listState.animateScrollToItem(0)
                }
                else -> Unit
            }
        }
    }

    LaunchedEffect(key1 = isKeyBoardOpen,
        block = {
            if (isKeyBoardOpen.name == "Closed") {
                listState.scrollToItem(0)
            }
        }
    )

    LaunchedEffect(key1 = true){
        rootViewModel.homeScreenEvent.collectLatest { value ->
            val event = value.uiEvent
            when(event){
                is UiEvent.Navigate->{
                   navHostController.navigate(route =event.route)
                }
                else->Unit
            }

        }
    }


    // Log.d(TAG, "HomeScreen: ${isKeyBoardOpen.name}")


    if (showCalendar) {
        CalendarDialog(
            rootViewModel = rootViewModel,
            onDismissRequest = {
                showCalendar = false
            }
        )


    }


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar()
        }
    ) {
        it.calculateTopPadding()

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(color = 0x93F1ECEC)),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Text(
                        text = "Enter purchase details :",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp, horizontal = 4.dp),
                        color = MaterialTheme.colors.error
                    )
                }

            }

            item {
                FirstThreeRows(
                    rootViewModel = rootViewModel,
                    hideKeyboard = hideKeyboard,
                    onSelectDateClicked = {
                        showCalendar = true
                    },
                    navHostController = navHostController,
                    onAddClientClicked = {
                        showAddClientDialog = true
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                ProductListTitle()
            }
            item {
                ProductList(rootViewModel = rootViewModel)
            }
            item {
                /* ItemSelectionRows(
                     homeScreenViewModel = homeScreenViewModel,
                     onFocusOnBasicTextField = { focused ->
                         if (focused) {
                             navHostController.navigate(RootNavScreens.ProductListScreen.route)
                         }

                     },
                     rootViewModel = rootViewModel,
                     hideKeyboard = hideKeyboard,
                     navHostController = navHostController
                 )*/

                ItemSelectionRows2(
                    rootViewModel = rootViewModel,
                    navHostController = navHostController,
                    hideKeyboard = hideKeyboard,
                    onAddProductClicked = { /*TODO*/ },
                    onQrScanClicked = {

                    }
                )
            }
            item {
                ProductButtonRow(
                    rootViewModel = rootViewModel
                )
            }
            item {
                ProductPriceColumn()
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                Button(onClick = {


                }) {
                    Text(text = "Submit")
                }
            }
            item {
                Spacer(modifier = Modifier.height(300.dp))
            }
        }


    }

}