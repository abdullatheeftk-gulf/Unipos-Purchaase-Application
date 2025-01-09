package com.gulfappdeveloper.project2.presentation.uni_licence_act_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.domain.datastore.UniLicenseDetails
import com.gulfappdeveloper.project2.navigation.root.RootNavScreens
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import com.gulfappdeveloper.project2.presentation.ui_util.screenSize
import com.gulfappdeveloper.project2.presentation.uni_licence_act_screen.components.LicenseInformationDisplayAlertDialog
import com.gulfappdeveloper.project2.ui.theme.OrangeColor
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UniLicenseActivationScreen(
    rootViewModel: RootViewModel,
    navHostController: NavHostController,
    hideKeyboard: () -> Unit,
    deviceId: String,
) {

   val screenWidth = screenSize().value


    val snackBarHostState = remember {
        SnackbarHostState()
    }

    var licenseKeyText by remember {
        mutableStateOf("")
    }

    val uniLicense by rootViewModel.uniLicenseDetails


    val licenseKeyActivationError by rootViewModel.licenseKeyActivationError

    val deviceIdFromDataStore by rootViewModel.deviceIdState

    var showProgressBar by remember {
        mutableStateOf(false)
    }

    var showAlertDialog by remember {
        mutableStateOf(false)
    }


    val scope = rememberCoroutineScope()

    val focusRequester by remember {
        mutableStateOf(FocusRequester())
    }

    LaunchedEffect(key1 = true) {

        focusRequester.requestFocus()

        rootViewModel.uniLicenseActivationUiEvent.collectLatest { value ->
            when (value.uiEvent) {
                is UiEvent.ShowAlertDialog -> {
                    showAlertDialog = true
                }
                is UiEvent.ShowProgressBar -> {
                    showProgressBar = true
                }
                is UiEvent.CloseProgressBar -> {
                    showProgressBar = false
                }
                is UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(value.uiEvent.message)
                }
                else -> Unit
            }
        }
    }

    if (showAlertDialog) {
        LicenseInformationDisplayAlertDialog(
            onDismissRequest = {

                navHostController.popBackStack()
                navHostController.navigate(route = RootNavScreens.SplashScreen2.route)
                rootViewModel.readBaseUrl2()
            },
            onLicenseExpired = {
                showAlertDialog = false
            },
            uniLicense = uniLicense
        )
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
                        "Activate App",
                        color = OrangeColor,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = if(screenWidth<600f) 20.sp else if(screenWidth>=600 && screenWidth<900) 24.sp else 28.sp
                    )
                },
            )
        }
    ) {paddingValues->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = paddingValues.calculateTopPadding())
        ) {
            Spacer(modifier = Modifier.height(15.dp))

            Row {
                Text(
                    text = "Device Id:-   ",
                    fontStyle = MaterialTheme.typography.headlineLarge.fontStyle,
                    fontSize = 20.sp
                )
                SelectionContainer() {
                    Text(
                        text = deviceIdFromDataStore.ifEmpty { deviceId },
                        fontStyle = MaterialTheme.typography.headlineLarge.fontStyle,
                        fontSize = 20.sp,
                    )
                }
            }
            uniLicense?.let { uniLicenseDetails: UniLicenseDetails ->
                Row {
                    Text(
                        text = "App License:-   ",
                        fontStyle = MaterialTheme.typography.headlineLarge.fontStyle,
                        fontSize = 20.sp
                    )
                    SelectionContainer() {
                        Text(
                            text = uniLicenseDetails.licenseKey,
                            fontStyle = MaterialTheme.typography.headlineLarge.fontStyle,
                            fontSize = 20.sp,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = licenseKeyText,
                onValueChange = { text ->
                    licenseKeyText = text
                    rootViewModel.setUnitActivationErrorValue("")
                },
                label = {
                    Text(
                        text = "Enter License Key",
                        fontSize = if(screenWidth<600f) 14.sp else if(screenWidth>=600 && screenWidth<900) 18.sp else 22.sp
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .focusRequester(focusRequester = focusRequester),
                isError = licenseKeyActivationError.isNotBlank() || licenseKeyActivationError.isNotEmpty(),
                keyboardActions = KeyboardActions(onDone = {
                    hideKeyboard()
                    if (!licenseKeyValidation(licenseKeyText)) {
                        scope.launch {
                           // scaffoldState.snackbarHostState.showSnackbar("Invalid License")
                            snackBarHostState.showSnackbar("Invalid License")
                        }
                        return@KeyboardActions
                    }


                    rootViewModel.uniLicenseActivation(
                        deviceId = deviceId,
                        licenseKey = licenseKeyText
                    )
                }),
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    capitalization = KeyboardCapitalization.Characters
                ),
                textStyle = TextStyle(
                    fontSize = if(screenWidth<600f) 14.sp else if(screenWidth>=600 && screenWidth<800) 18.sp else 22.sp
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp)
            ) {
                if (licenseKeyActivationError.isNotBlank() || licenseKeyActivationError.isNotEmpty()) {
                    Text(
                        text = if (licenseKeyActivationError == "Expired License") "Expired License" else "Bad Request",
                        color = MaterialTheme.colorScheme.error,
                        fontSize = if(screenWidth<600f) 14.sp else if(screenWidth>=600 && screenWidth<900) 18.sp else 22.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {
                    hideKeyboard()
                    if (!licenseKeyValidation(licenseKeyText)) {
                        scope.launch {
                            snackBarHostState.showSnackbar("Invalid License")
                        }
                        return@Button
                    }

                    rootViewModel.uniLicenseActivation(
                        deviceId = deviceId,
                        licenseKey = licenseKeyText
                    )

                },
                enabled = !showProgressBar
            ) {
                Text(
                    text = "Activate",
                    fontSize = if(screenWidth<600f) 14.sp else if(screenWidth>=600 && screenWidth<900) 18.sp else 22.sp
                )
            }



        }

        if (showProgressBar) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(20.dp))
            }
        }

    }

}

private fun licenseKeyValidation(value: String): Boolean {
    return value.startsWith('P', ignoreCase = false)
}


