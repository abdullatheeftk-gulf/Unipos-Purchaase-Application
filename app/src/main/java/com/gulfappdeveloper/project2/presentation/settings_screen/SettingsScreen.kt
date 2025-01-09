package com.gulfappdeveloper.project2.presentation.settings_screen

import android.util.Patterns
import android.webkit.URLUtil
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.navigation.root.RootNavScreens
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import com.gulfappdeveloper.project2.presentation.ui_util.screenSize
import com.gulfappdeveloper.project2.ui.theme.OrangeColor
import kotlinx.coroutines.flow.collectLatest


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    rootViewModel: RootViewModel,
    hideKeyboard: () -> Unit,
    navHostController: NavHostController,
    settingScreenViewModel: SettingScreenViewModel = hiltViewModel()
) {

    val screenWidth = screenSize().value

    val snackBarHostState = remember {
        SnackbarHostState()
    }

    var text by remember {
        mutableStateOf("")
    }


    var showProgressBar by remember {
        mutableStateOf(false)
    }

    val uniLicenseDetails by rootViewModel.uniLicenseDetails


    val currentBaseUrl by rootViewModel.baseUrl

    LaunchedEffect(key1 = true) {
        settingScreenViewModel.uiEvent.collectLatest { value: UiEvent ->
            when (value) {
                is UiEvent.Navigate -> {
                    rootViewModel.setIsInitialLoadingIsNotFinished()
                    navHostController.popBackStack(route = RootNavScreens.MainScreen.route,inclusive = true)
                    navHostController.navigate(route = RootNavScreens.LoginScreen.route)

                }
                is UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(
                        message = value.message,
                        duration = SnackbarDuration.Long
                    )
                }
                is UiEvent.ShowProgressBar -> {
                    showProgressBar = true
                }
                is UiEvent.CloseProgressBar -> {
                    showProgressBar = false
                }
                else -> Unit
            }
        }
    }

    BackHandler(true) {
        if (!showProgressBar) {
            navHostController.popBackStack()
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        topBar = {
            TopAppBar(
                modifier = Modifier.shadow(elevation = 6.dp),
                title = {
                    Text(
                        text = "Settings",
                        color = OrangeColor,
                        fontSize = if (screenWidth < 600f) 20.sp else if (screenWidth >= 600 && screenWidth < 800) 22.sp else 28.sp,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        if (!showProgressBar) {
                            navHostController.popBackStack()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = OrangeColor
                        )
                    }
                },
                actions = {
                    TextButton(
                        onClick = {
                            settingScreenViewModel.setLogout()
                        },
                    ) {
                        Text(
                            text = "LOGOUT",
                            color = MaterialTheme.colorScheme.error,
                            fontSize = if (screenWidth < 600f) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                        )
                    }
                }
            )
        },

        ) {paddingValues->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(paddingValues.calculateTopPadding()))

            OutlinedTextField(
                value = text,
                onValueChange = { typedText ->
                    text = typedText
                },
                placeholder = {
                    Text(
                        text = currentBaseUrl,
                        modifier = Modifier.alpha(0.3f),
                        fontSize = if (screenWidth < 600f) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Uri,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {

                        hideKeyboard()
                        if (urlValidator(baseUrl = text)) {
                            settingScreenViewModel.setBaseUrl(value = text)
                        } else {
                            settingScreenViewModel.onErrorUrl(url = text)
                        }
                    }
                ),
                modifier = Modifier
                    .fillMaxWidth(),
                enabled = !showProgressBar,
                textStyle = TextStyle(
                    fontSize = if (screenWidth < 600f) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {

                    hideKeyboard()
                    if (!showProgressBar) {
                        if (urlValidator(baseUrl = text)) {
                            settingScreenViewModel.setBaseUrl(value = text)
                        } else {
                            settingScreenViewModel.onErrorUrl(url = text)
                        }
                    }
                },
                enabled = !showProgressBar
            ) {
                Text(
                    text = "Set Base Url",
                    fontSize = if (screenWidth < 600f) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            uniLicenseDetails?.let {uniLicense->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "App License",
                        fontSize = if (screenWidth < 600f) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                    )
                    Text(
                        text = " : ",
                        fontSize = if (screenWidth < 600f) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                    )
                    Text(
                        text = uniLicense.licenseKey,
                        color = OrangeColor,
                        fontSize = if (screenWidth < 600f) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                    )

                }
            }
        }

        if (showProgressBar) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

    }

}

private fun urlValidator(baseUrl: String): Boolean {
    return try {
        URLUtil.isValidUrl(baseUrl) && Patterns.WEB_URL.matcher(baseUrl).matches()
    } catch (e: Exception) {
        false
    }
}