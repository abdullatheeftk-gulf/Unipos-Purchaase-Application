package com.gulfappdeveloper.project2.presentation.add_client_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.domain.models.remote.get.ClientDetails
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddClientScreen(
    rootViewModel: RootViewModel,
    hideKeyboard: () -> Unit,
    navHostController: NavHostController,
    addClientViewModel: AddClientViewModel = hiltViewModel()
) {
    val baseUrl by rootViewModel.baseUrl

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


    val scaffoldState = rememberScaffoldState()

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
                    scaffoldState.snackbarHostState.showSnackbar(value.message)
                }
                is UiEvent.Navigate -> {
                    try {
                        rootViewModel.setClientDetails(
                            value = ClientDetails(
                                clientName = accountName,
                                // Receiving result account id through the event
                                clientId = value.route.toInt()
                            )
                        )
                        navHostController.popBackStack()
                    } catch (e: Exception) {
                        e.printStackTrace()
                        scaffoldState.snackbarHostState.showSnackbar("There have some error when receiving account id")
                    }
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = {
                Text(text = "Add Client")
            },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                })
        }
    ) {
        if (showProgressBar){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }

        it.calculateTopPadding()
        Column(
            modifier = Modifier
                .padding(all = 8.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

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
                                        color = MaterialTheme.colors.error,
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
                    imeAction = ImeAction.Next
                ),
                isError = showClientNameError
            )
            if (showClientNameError) {
                Text(
                    text = "    Client name is required",
                    color = MaterialTheme.colors.error,
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
                                        color = MaterialTheme.colors.error,
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
                    imeAction = ImeAction.Next
                ),
                isError = showClientIdError
            )
            if (showClientIdError) {
                Text(
                    text = "    Client Id is required",
                    color = MaterialTheme.colors.error,
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
                    imeAction = ImeAction.Next
                )
            )


            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = " Address Details",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = MaterialTheme.colors.primary,
                fontStyle = MaterialTheme.typography.h6.fontStyle,
                fontSize = MaterialTheme.typography.h6.fontSize,
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

            Spacer(modifier = Modifier.height(25.dp))
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
                addClientViewModel.addClientFun(baseUrl = baseUrl)
            },
                enabled = !showProgressBar
            ) {
                Text(text = "Add")
            }

            Spacer(modifier = Modifier.height(200.dp))

        }
    }
}