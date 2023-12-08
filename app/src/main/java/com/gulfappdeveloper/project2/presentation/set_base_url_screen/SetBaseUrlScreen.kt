package com.gulfappdeveloper.project2.presentation.set_base_url_screen

import android.util.Patterns
import android.webkit.URLUtil
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import com.gulfappdeveloper.project2.ui.theme.OrangeColor
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetBaseUrlScreen(
    rootViewModel: RootViewModel,
    navHostController: NavHostController,
    hideKeyboard: () -> Unit,
    setBaseUrlScreenViewModel: SetBaseUrlScreenViewModel = hiltViewModel()
) {
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    val currentBaseUrl by rootViewModel.baseUrl

    var text by remember {
        mutableStateOf("")
    }
    val focusRequester by remember {
        mutableStateOf(FocusRequester())
    }

    var showProgressBar by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        focusRequester.requestFocus()
        setBaseUrlScreenViewModel.uiEvent.collectLatest { value: UiEvent ->
            when (value) {
                is UiEvent.Navigate -> {
                    navHostController.popBackStack()
                    navHostController.navigate(value.route)
                }
                is UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(value.message, duration = SnackbarDuration.Long)
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

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        topBar = {
            TopAppBar(
                modifier = Modifier.shadow(elevation = 6.dp),
                title = {
                    Text(
                        text = "Set Base Url",
                        color = OrangeColor,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
            )
        }
    ) {paddingValues->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = paddingValues.calculateTopPadding()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = text,
                onValueChange = { typedText ->
                    text = typedText
                },
                placeholder = {
                    Text(text = currentBaseUrl)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Uri,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        hideKeyboard()
                        if (urlValidator(baseUrl = text)) {
                            setBaseUrlScreenViewModel.setBaseUrl(value = text)
                        } else {
                            setBaseUrlScreenViewModel.onErrorUrl(url = text)
                        }
                    }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester = focusRequester),
                enabled = !showProgressBar
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    hideKeyboard()
                    if (urlValidator(baseUrl = text)) {
                        setBaseUrlScreenViewModel.setBaseUrl(value = text)
                    } else {
                        setBaseUrlScreenViewModel.onErrorUrl(url = text)
                    }
                },
                enabled = !showProgressBar
            ) {
                Text(text = "Set Base Url")
            }
        }
        if (showProgressBar) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(20.dp))
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