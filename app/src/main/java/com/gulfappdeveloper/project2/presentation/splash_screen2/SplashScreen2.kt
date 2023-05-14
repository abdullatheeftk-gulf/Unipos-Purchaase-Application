package com.gulfappdeveloper.project2.presentation.splash_screen2

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.R
import com.gulfappdeveloper.project2.navigation.root.RootNavScreens
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import com.gulfappdeveloper.project2.ui.theme.OrangeColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreen2(
    navHostController: NavHostController,
    rootViewModel: RootViewModel,
   // deviceId: String
) {
    val scaffoldState = rememberScaffoldState()

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
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.uiEvent.message
                    )
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
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
                    color = MaterialTheme.colors.OrangeColor
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
                        .width(200.dp)
                        .height(150.dp),
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
                    Text(text = "Set Base Url")
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