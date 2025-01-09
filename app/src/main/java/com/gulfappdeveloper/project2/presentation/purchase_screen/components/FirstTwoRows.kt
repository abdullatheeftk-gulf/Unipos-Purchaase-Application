package com.gulfappdeveloper.project2.presentation.purchase_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.navigation.root.RootNavScreens
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.ui_util.screenSize
import com.gulfappdeveloper.project2.ui.theme.OrangeColor
import java.text.SimpleDateFormat
import java.util.*



@Composable
fun FirstTwoRows(
    rootViewModel: RootViewModel,
    navHostController: NavHostController,
    onSelectDateClicked: () -> Unit,
    showBillNoError: Boolean,
    onBillNoError: () -> Unit,
    showClientError: Boolean,
    onClientError: () -> Unit,
    onAddClientClicked: () -> Unit,
    hideKeyboard: () -> Unit
) {

    val screenWidth = screenSize().value

    val selectedDate by rootViewModel.selectedDate


    val billNo by rootViewModel.billNo

    val selectedClient by rootViewModel.selectedClient


    val focusRequester = remember {
        FocusRequester()
    }


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
                    onBillNoError()
                    rootViewModel.setBillNo(it)
                },
                label = {
                    Text(
                        text = "Bill no.",
                        fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp),
                shape = MaterialTheme.shapes.medium,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    capitalization = KeyboardCapitalization.Characters
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        hideKeyboard()
                    }
                ),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                ),
                isError = showBillNoError,
            )

            OutlinedTextField(
                value = SimpleDateFormat("d MMM yyyy", Locale.getDefault()).format(selectedDate),
                onValueChange = {

                },
                label = {
                    Text(
                        text = "Date",
                        fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp)
                    .clickable {
                        onSelectDateClicked()
                    }
                    .onFocusChanged {

                        if (it.hasFocus) {
                            onSelectDateClicked()
                        }
                    }
                    .focusRequester(focusRequester),
                readOnly = true,
                shape = MaterialTheme.shapes.medium,
                maxLines = 1,
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(
            if(screenWidth<600) 2.dp else if(screenWidth>=600 && screenWidth>800) 4.dp else 6.dp
        ))


        OutlinedTextField(
            value = selectedClient?.clientName?:"",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            textStyle = TextStyle(
                fontSize = if(screenWidth<600) 14.sp else if(screenWidth>=600 && screenWidth<800) 18.sp else 22.sp
            ),
            maxLines = 1,
            readOnly = true,
            enabled = false,
            shape = MaterialTheme.shapes.medium,
            isError = showClientError,
            label = {
                    Text(
                        text = if(selectedClient?.clientName == null) "Select a Client" else "Client",
                        fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
                    )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    tint = OrangeColor,
                    contentDescription = null,
                    modifier = Modifier.weight(1f)
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                disabledBorderColor = MaterialTheme.colorScheme.outline,
                disabledTextColor = MaterialTheme.colorScheme.primary,
                unfocusedTextColor = MaterialTheme.colorScheme.primary,
                disabledLabelColor = MaterialTheme.colorScheme.onSurface,
            ),
            trailingIcon = {
                Row {
                    IconButton(
                        onClick = {
                            onClientError()
                            rootViewModel.getClientDetails()
                            navHostController.navigate(route = RootNavScreens.ClientListScreen.route)
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = null,
                            tint = OrangeColor,
                            modifier = Modifier.size(
                                if(screenWidth<600) 24.dp else if(screenWidth>=600 && screenWidth<800) 30.dp else 36.dp
                            )
                        )
                    }
                    IconButton(
                        onClick = {
                            onAddClientClicked()
                            onClientError()
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(
                                if(screenWidth<600) 24.dp else if(screenWidth>=600 && screenWidth<800) 30.dp else 36.dp
                            )
                        )
                    }
                }
            }
        )


        if (showClientError) {
            Text(
                text = "    Client is not Selected",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.Start),
                fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp
            )
        }

        Spacer(modifier = Modifier.height(
            if(screenWidth<600) 2.dp else if(screenWidth>=600 && screenWidth<800) 4.dp else 8.dp
        ))

    }


}



