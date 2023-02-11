package com.gulfappdeveloper.project2.presentation.purchase_screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.navigation.root.RootNavScreens
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.ui.theme.OrangeColor
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun FirstThreeRows(
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


    val selectedDate by rootViewModel.selectedDate



    val billNo by rootViewModel.billNo

    val selectedClient by rootViewModel.selectedClient


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
                    onBillNoError()
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
                    imeAction = ImeAction.Done,
                    capitalization = KeyboardCapitalization.Characters
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        hideKeyboard()
                    }
                ),
                textStyle = TextStyle(
                    color = MaterialTheme.colors.primary
                ),
                isError = showBillNoError
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

                        if (it.hasFocus) {
                            onSelectDateClicked()
                            focusManager.clearFocus()
                        }
                    }
                    .focusRequester(focusRequester),
                readOnly = true,
                shape = MaterialTheme.shapes.medium,
                maxLines = 1,
                textStyle = TextStyle(
                    color = MaterialTheme.colors.primary
                )
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
                    color = if (showClientError) MaterialTheme.colors.error else Color.LightGray,
                    shape = MaterialTheme.shapes.medium
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Person,
                tint = MaterialTheme.colors.OrangeColor,
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
                    onClientError()
                    rootViewModel.getClientDetails()
                    navHostController.navigate(route = RootNavScreens.ClientListScreen.route)
                },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colors.OrangeColor,
                )
            }
            IconButton(
                onClick = {
                    onAddClientClicked()
                    onClientError()
                },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }

        }
        if (showClientError) {
            Text(
                text = "    Client is not Selected",
                color = MaterialTheme.colors.error,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        Spacer(modifier = Modifier.height(2.dp))

        //Third Row

        /* Row(modifier = Modifier.fillMaxWidth()) {

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


         }*/
    }


}



