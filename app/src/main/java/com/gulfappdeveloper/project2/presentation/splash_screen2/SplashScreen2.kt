package com.gulfappdeveloper.project2.presentation.splash_screen2

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.R
import com.gulfappdeveloper.project2.navigation.root.RootNavScreens
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import com.gulfappdeveloper.project2.presentation.ui_util.screenSize
import com.gulfappdeveloper.project2.ui.theme.OrangeColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreen2(
    navHostController: NavHostController,
    rootViewModel: RootViewModel,
) {
    val screenWidth = screenSize().value

    val snackBarHostState = remember {
        SnackbarHostState()
    }

    val welcomeMessage by rootViewModel.message

    var showLogoImage by remember {
        mutableStateOf(false)
    }
    if (welcomeMessage.isNotEmpty()) {
        showLogoImage = true
    }

    val density = LocalDensity.current

    var showProgressBar by remember {
        mutableStateOf(false)
    }

    var showUrlSetButton by remember {
        mutableStateOf(false)
    }


    LaunchedEffect(key1 = true) {
        rootViewModel.splashScreenEvent2.collectLatest { event ->
            Log.i("Test", "Spl ")
            when (event.uiEvent) {
                is UiEvent.ShowProgressBar -> {
                    showProgressBar = true
                }
                is UiEvent.CloseProgressBar -> {
                    showProgressBar = false
                }
                is UiEvent.Navigate -> {
                    Log.w("Test", "SplashScreen2: ", )
                    delay(2000L)
                   // rootViewModel.saveDeviceId(value = deviceId)
                    navHostController.popBackStack()
                    navHostController.navigate(route = event.uiEvent.route)
                }
                is UiEvent.ShowButton1 -> {
                    showUrlSetButton = true
                }
                is UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(event.uiEvent.message)
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
    ) {
        it.calculateTopPadding()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            Image(
                painter = painterResource(id = R.drawable.outline_shopping_cart_24),
                contentDescription = null,
                modifier = Modifier.size(250.dp),
                colorFilter = ColorFilter.tint(
                    color = OrangeColor
                )
            )
            Spacer(modifier = Modifier.height(50.dp))


            AnimatedVisibility(
                visible = showLogoImage,
                enter = slideInVertically {
                    with(density) {
                        100.dp.roundToPx()
                    }
                } + expandVertically(
                    expandFrom = Alignment.Top,

                    )
                        + fadeIn(initialAlpha = 0.3f),
                exit = slideOutVertically() + shrinkVertically() + fadeOut()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .width(if(screenWidth<600f) 200.dp else if(screenWidth>=600 && screenWidth<900) 300.dp else 350.dp )
                        .height(if(screenWidth<600f) 150.dp else if(screenWidth>=600 && screenWidth<900) 200.dp else 250.dp),
                    alignment = Alignment.Center
                )
            }


        }

        if (showUrlSetButton) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Button(
                    onClick = {
                        navHostController.popBackStack()
                        navHostController.navigate(route = RootNavScreens.UrlSetScreen.route)
                    }
                ) {
                    Text(
                        text = "Set Base Url",
                        fontSize = if(screenWidth<600f) 14.sp else if(screenWidth>=600 && screenWidth<900) 18.sp else 22.sp
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }


        if (showProgressBar) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(50.dp))
            }

        }


    }
}