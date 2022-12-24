package com.gulfappdeveloper.project2.presentation.home_screen.components

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.domain.models.util.PayMode
import com.gulfappdeveloper.project2.navigation.root.RootNavScreens
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "FirstThreeRows"


@Composable
fun FirstThreeRows(
    rootViewModel: RootViewModel,
    navHostController: NavHostController,
    onSelectDateClicked: () -> Unit,
    onAddClientClicked: () -> Unit,
    hideKeyboard: () -> Unit
) {

    var showDropDownMenu by remember {
        mutableStateOf(false)
    }

    val selectedDate by rootViewModel.selectedDate

    val billNo by rootViewModel.billNo

    val selectedClient by rootViewModel.selectedClient

    val poNo by rootViewModel.poNo

    val payMode by rootViewModel.payMode


    val focusRequester = remember {
        FocusRequester()
    }

    val focusManager = LocalFocusManager.current





    Spacer(modifier = Modifier.height(5.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //First row
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            OutlinedTextField(
                value = billNo,
                onValueChange = {
                    rootViewModel.setBillNo(it)
                },
                label = {
                    Text(text = "Bill no.")
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp),
                shape = MaterialTheme.shapes.medium,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        hideKeyboard()
                    }
                )
            )

            OutlinedTextField(
                value = SimpleDateFormat("d MMM yyyy", Locale.getDefault()).format(selectedDate),
                onValueChange = {

                },
                label = {
                    Text(text = "Date")
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp)
                    .clickable {
                        onSelectDateClicked()
                    }
                    .onFocusChanged {
                        Log.e(
                            TAG,
                            "FirstThreeRows: ${it.hasFocus} ${it.hasFocus} ${it.isCaptured}",
                        )

                        if (it.hasFocus) {
                            onSelectDateClicked()
                            focusManager.clearFocus()
                        }
                    }
                    .focusRequester(focusRequester),
                readOnly = true,
                shape = MaterialTheme.shapes.medium,
                maxLines = 1
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        //Second row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = MaterialTheme.shapes.medium
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Person,
                tint = MaterialTheme.colors.error,
                contentDescription = null,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = selectedClient?.clientName ?: "Select a Client",
                modifier = Modifier
                    .weight(7f)
                    .padding(start = 4.dp),
                color = if (selectedClient == null) Color.DarkGray else MaterialTheme.colors.primary
            )
            IconButton(
                onClick = {
                    rootViewModel.getClientDetails()
                    navHostController.navigate(route = RootNavScreens.ClientListScreen.route)
                },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colors.error,
                )
            }
            IconButton(
                onClick = { onAddClientClicked() },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }

        }

        Spacer(modifier = Modifier.height(2.dp))

        //Third Row

        Row(modifier = Modifier.fillMaxWidth()) {

            OutlinedTextField(
                value = poNo,
                onValueChange = {
                    rootViewModel.setPoNo(value = it)
                },
                label = {
                    Text(text = "Po No.")
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        hideKeyboard()
                    }
                )
            )
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp)
            ) {
                OutlinedTextField(
                    value = payMode.name,
                    onValueChange = {
                        rootViewModel.setPayMode(PayMode.valueOf(it))
                    },
                    label = {
                        Text(text = "Pay Mode")
                    },
                    modifier = Modifier
                        .weight(1f),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                showDropDownMenu = true
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                tint = MaterialTheme.colors.error,
                                contentDescription = null
                            )
                        }

                    },
                    maxLines = 1,
                    readOnly = true,
                )
                DropdownMenu(
                    expanded = showDropDownMenu,
                    onDismissRequest = {
                        showDropDownMenu = false
                    },
                    properties = PopupProperties(
                        focusable = true
                    ),
                ) {
                    PayMode.values().forEach { value ->
                        DropdownMenuItem(
                            onClick = {
                                rootViewModel.setPayMode(value)
                                showDropDownMenu = false
                            },
                            contentPadding = MenuDefaults.DropdownMenuItemContentPadding,
                        ) {
                            Text(text = value.name)
                        }
                    }
                }
            }


        }
    }


}



