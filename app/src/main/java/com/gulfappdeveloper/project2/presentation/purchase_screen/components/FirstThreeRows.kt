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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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

private const val TAG = "FirstThreeRows"

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
                    color = MaterialTheme.colorScheme.primary
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
                        }
                    }
                    .focusRequester(focusRequester),
                readOnly = true,
                shape = MaterialTheme.shapes.medium,
                maxLines = 1,
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.primary
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
                    color = if (showClientError) MaterialTheme.colorScheme.error else Color.LightGray,
                    shape = MaterialTheme.shapes.medium
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Person,
                tint = OrangeColor,
                contentDescription = null,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = selectedClient?.clientName ?: "Select a Client",
                modifier = Modifier
                    .weight(7f)
                    .padding(start = 4.dp),
                color = if (selectedClient == null) Color.DarkGray else MaterialTheme.colorScheme.primary
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
                    tint = OrangeColor,
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
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        Spacer(modifier = Modifier.height(2.dp))

    }


}



