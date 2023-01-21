package com.gulfappdeveloper.project2.presentation.login_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.R
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import com.gulfappdeveloper.project2.ui.theme.OrangeColor
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    rootViewModel: RootViewModel,
    navHostController: NavHostController,
    hideKeyboard: () -> Unit,
) {

    val scaffoldState = rememberScaffoldState()


    var text by remember {
        mutableStateOf("")
    }

    val focusRequester by remember {
        mutableStateOf(FocusRequester())
    }

    var showProgressBar by remember {
        mutableStateOf(false)
    }

    var showPasswordToggle by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        focusRequester.requestFocus()
        rootViewModel.loginScreenEvent.collectLatest { value ->
            when (val event = value.uiEvent) {
                is UiEvent.ShowProgressBar -> {
                    showProgressBar = true
                }
                is UiEvent.CloseProgressBar -> {
                    showProgressBar = false
                }
                is UiEvent.Navigate -> {
                    navHostController.popBackStack()
                    navHostController.navigate(event.route)
                }
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Login")
                },
            )
        }
    ) {
        it.calculateTopPadding()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = text,
                trailingIcon = {
                    IconButton(onClick = {
                        showPasswordToggle = !showPasswordToggle
                    }) {
                        Icon(
                            painter = painterResource(
                                id = if (showPasswordToggle) R.drawable.baseline_visibility_off_24 else R.drawable.baseline_visibility_24
                            ),
                            contentDescription = null,
                            tint = MaterialTheme.colors.OrangeColor
                        )
                    }
                },
                visualTransformation = if (showPasswordToggle) VisualTransformation.None else PasswordVisualTransformation(),
                onValueChange = { typedText ->
                    text = typedText
                },
                label = {
                    Text(text = "User Password")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        hideKeyboard()
                        rootViewModel.loginUser(password = text)
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
                    rootViewModel.loginUser(password = text)

                },
                enabled = !showProgressBar
            ) {
                Text(text = "Login")
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

