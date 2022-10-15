package com.gulfappdeveloper.project2.presentation.splash_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.navigation.root.RootNavScreens
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.splash_screen.util.SplashScreenEvent
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import kotlinx.coroutines.flow.collectLatest

private const val TAG = "SplashScreen"

@Composable
fun SplashScreen(
    navHostController: NavHostController,
    rootViewModel: RootViewModel,
    splashScreenViewModel: SplashScreenViewModel = hiltViewModel()
) {

    val localContext = LocalContext.current

    val welcomeMessage by rootViewModel.message

    val scaffoldState = rememberScaffoldState()

    var showProgressBar by remember {
        mutableStateOf(false)
    }

    var showSetBaseUrlButton by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = welcomeMessage) {
        Log.i(TAG, "SplashScreen: ")
        splashScreenViewModel.navigateToNextScreen(welcomeMessage)
    }
    LaunchedEffect(key1 = true) {

        splashScreenViewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    navHostController.popBackStack()
                    navHostController.navigate(event.route)
                }
                else -> Unit
            }
        }
    }

    LaunchedEffect(key1 = true) {
        rootViewModel.splashScreenEvent.collectLatest { event ->
            when (event) {
                is SplashScreenEvent.ShowProgressBar -> {
                    showProgressBar = true
                }
                is SplashScreenEvent.CloseProgressBar -> {
                    showProgressBar = false
                }
                is SplashScreenEvent.ShowToast -> {
                    Toast.makeText(localContext, event.message, Toast.LENGTH_LONG).show()
                }
                is SplashScreenEvent.ShowSetBaseUrlButton -> {
                    showSetBaseUrlButton = true
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        it.calculateTopPadding()
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = welcomeMessage)
        }
        if (showProgressBar) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(100.dp))
            }
        }

        if (showSetBaseUrlButton) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        showSetBaseUrlButton = false
                        navHostController.popBackStack()
                        navHostController.navigate(RootNavScreens.UrlSetScreen.route)
                    }
                ) {
                    Text(text = "Set Base Url")
                }
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }

}