package com.gulfappdeveloper.project2.presentation.add_client_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.domain.models.remote.get.ClientDetails
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import com.gulfappdeveloper.project2.ui.theme.OrangeColor
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddClientScreen(
    rootViewModel: RootViewModel,
    hideKeyboard: () -> Unit,
    navHostController: NavHostController,
    addClientViewModel: AddClientViewModel = hiltViewModel()
) {

    // Add client screen navigation popUp flag, false is for navigation from the main screen
    val addClientScreenNavPopUpFlag by rootViewModel.addClientScreenNavPopUpFlag

    var showClientNameError by remember {
        mutableStateOf(false)
    }

    var showClientIdError by remember {
        mutableStateOf(false)
    }

    var showProgressBar by remember {
        mutableStateOf(false)
    }

    val accountName by addClientViewModel.accountName

    val streetName by addClientViewModel.streetName

    val buildingNumber by addClientViewModel.buildingNumber

    val plotIdentification by addClientViewModel.plotIdentification

    val citySubdivisionName by addClientViewModel.citySubDivisionName

    val cityName by addClientViewModel.cityName

    val postalZone by addClientViewModel.postalZone

    val countrySubEntity by addClientViewModel.countrySubEntity

    val country by addClientViewModel.country

    val nat by addClientViewModel.nat

    val phoneOne by addClientViewModel.phoneOne

    val phoneTwo by addClientViewModel.phoneTwo

    val taxId by addClientViewModel.taxId


    val snackBarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(key1 = true) {
        addClientViewModel.addClientEvent.collectLatest { result ->
            when (val value = result.uiEvent) {
                is UiEvent.ShowProgressBar -> {
                    showProgressBar = true
                }
                is UiEvent.CloseProgressBar -> {
                    showProgressBar = false
                }
                is UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(message = value.message)
                }
                is UiEvent.Navigate -> {
                    try {
                        if (addClientScreenNavPopUpFlag) {
                            rootViewModel.setClientDetails(
                                value = ClientDetails(
                                    clientName = accountName,
                                    // Receiving result account id through the event
                                    clientId = value.route.toInt()
                                )
                            )
                        }
                        navHostController.popBackStack()
                    } catch (e: Exception) {
                        snackBarHostState.showSnackbar(e.message?:"There have some error when receiving account id")
                    }
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
                        text = "Add Client",
                        color = OrangeColor
                    )
                },
               // backgroundColor = MaterialTheme.colorScheme.surface,
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = OrangeColor
                        )
                    }
                })
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            Button(
                onClick = {
                    if (accountName.isEmpty()) {
                        showClientNameError = true
                        return@Button
                    }
                    if (taxId.isEmpty()) {
                        showClientIdError = true
                        return@Button
                    }
                    addClientViewModel.addClientFun()
                },
                enabled = !showProgressBar
            ) {
                Text(text = "Add Client")
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
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 0.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            
            Spacer(modifier = Modifier.height(paddingValues.calculateTopPadding()))

            OutlinedTextField(
                value = accountName,
                onValueChange = { value ->
                    showClientNameError = false
                    addClientViewModel.setAccountName(value)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                    Row() {
                        Text(text = "Client Name ")
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = MaterialTheme.colorScheme.error,
                                        baselineShift = BaselineShift.Superscript
                                    )
                                ) {
                                    append("*")
                                }
                            },
                        )
                    }

                },
                maxLines = 2,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                isError = showClientNameError,
                keyboardActions = KeyboardActions(
                    onDone = {
                        hideKeyboard()
                    }
                )
            )
            if (showClientNameError) {
                Text(
                    text = "    Client name is required",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Start)
                )
            }


            OutlinedTextField(
                value = taxId,
                onValueChange = { value ->
                    showClientIdError = false
                    addClientViewModel.setTaxId(value)
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Row() {
                        Text(text = "Tax Id ")
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = MaterialTheme.colorScheme.error,
                                        baselineShift = BaselineShift.Superscript
                                    )
                                ) {
                                    append("*")
                                }
                            },
                        )
                    }
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    capitalization = KeyboardCapitalization.Characters
                ),
                isError = showClientIdError,
                keyboardActions = KeyboardActions(
                    onDone = {
                        hideKeyboard()
                    }
                )
            )
            if (showClientIdError) {
                Text(
                    text = "    Client Id is required",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
            OutlinedTextField(
                value = nat,
                onValueChange = { value ->
                    addClientViewModel.setNat(value)
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Nat")
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        hideKeyboard()
                    }
                )
            )


            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Address Details",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = MaterialTheme.colorScheme.primary,
                fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
                fontSize = 20.sp,
                textDecoration = TextDecoration.Underline
            )


            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = buildingNumber,
                    onValueChange = { value ->
                        addClientViewModel.setBuildingNumber(value)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(end = 4.dp),
                    label = {
                        Text(text = "Building Number")
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text
                    )
                )
                OutlinedTextField(
                    value = plotIdentification,
                    onValueChange = { value ->
                        addClientViewModel.setPlotIdentification(value)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 4.dp),
                    label = {
                        Text(text = "Plot Identification")
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    )
                )
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = streetName,
                    onValueChange = { value ->
                        addClientViewModel.setStreetName(value)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(end = 4.dp),
                    label = {
                        Text(text = "Street Name")
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                )
                OutlinedTextField(
                    value = postalZone,
                    onValueChange = { value ->
                        addClientViewModel.setPostalZone(value)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 4.dp),
                    label = {
                        Text(text = "Postal Zone")
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                )
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = cityName,
                    onValueChange = { value ->
                        addClientViewModel.setCityName(value)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(end = 4.dp),
                    label = {
                        Text(text = "City")
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    )
                )
                OutlinedTextField(
                    value = citySubdivisionName,
                    onValueChange = { value ->
                        addClientViewModel.setCitySubDivisionName(value)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 4.dp),
                    label = {
                        Text(text = "Local Area")
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    )
                )
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = country,
                    onValueChange = { value ->
                        addClientViewModel.setCountry(value)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(end = 4.dp),
                    label = {
                        Text(text = "Country")
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    )
                )
                OutlinedTextField(
                    value = countrySubEntity,
                    onValueChange = { value ->
                        addClientViewModel.setCountrySubEntity(value)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 4.dp),
                    label = {
                        Text(text = "Province/State")
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            hideKeyboard()
                        }
                    )
                )
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = phoneOne,
                    onValueChange = { value ->
                        addClientViewModel.setPhoneOne(value)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(end = 4.dp),
                    label = {
                        Text(text = "Phone No 1")
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    )
                )
                OutlinedTextField(
                    value = phoneTwo,
                    onValueChange = { value ->
                        addClientViewModel.setPhoneTwo(value)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 4.dp),
                    label = {
                        Text(text = "Phone No 2")
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            hideKeyboard()
                        }
                    )
                )
            }


            Spacer(modifier = Modifier.height(200.dp))

        }
    }
}