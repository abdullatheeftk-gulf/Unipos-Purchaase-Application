package com.gulfappdeveloper.project2.presentation.home_screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "FirstThreeRows"

@Composable
fun FirstThreeRows(
    onSelectDateClicked: () -> Unit
) {

    var billNo by remember {
        mutableStateOf("")
    }
    var dateSelected by remember {
        mutableStateOf(Date())
    }
    var poNo by remember {
        mutableStateOf("")
    }

    var paymentMode by remember {
        mutableStateOf("Cash")
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
                    billNo = it
                },
                label = {
                    Text(text = "Bill no.")
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp),
                shape = MaterialTheme.shapes.medium,
                maxLines = 1
            )

            OutlinedTextField(
                value = SimpleDateFormat(
                    "d MMM yyyy",
                    Locale.getDefault()
                ).format(dateSelected),
                onValueChange = {

                },
                label = {
                    Text(text = "Date")
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp)
                    .onFocusChanged { focusState ->
                        if (focusState.hasFocus) {
                            onSelectDateClicked()
                        }
                    },
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
                text = "Select Client",
                modifier = Modifier
                    .weight(7f)
                    .padding(start = 4.dp)
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colors.error,
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = { /*TODO*/ },
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

                },
                label = {
                    Text(text = "Po No.")
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp),
                maxLines = 1
            )

            OutlinedTextField(
                value = paymentMode,
                onValueChange = {

                },
                label = {
                    Text(text = "Pay Mode")
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        tint = MaterialTheme.colors.error,
                        contentDescription = null
                    )
                },
                maxLines = 1
            )
        }
    }


}



