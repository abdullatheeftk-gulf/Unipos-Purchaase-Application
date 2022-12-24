package com.gulfappdeveloper.project2.presentation.home_screen.components

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.gulfappdeveloper.project2.domain.models.remote.get.ClientDetails
import com.gulfappdeveloper.project2.domain.models.remote.get.WelcomeMessage
import com.gulfappdeveloper.project2.navigation.root.RootViewModel

@Composable
fun AddClientDialog(
    onDismissRequest:()->Unit,
    rootViewModel: RootViewModel

) {
    var clientName by remember {
        mutableStateOf("")
    }

    var taxId by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismissRequest,
        shape = RoundedCornerShape(percent = 4),
        buttons = {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .wrapContentSize(align = Alignment.Center)
                    .padding(15.dp)
            ) {
                Text(
                    text = "Add Client",
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.h6,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    textDecoration = TextDecoration.Underline
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = clientName,
                    onValueChange = {
                        clientName = it
                    },
                    maxLines = 1,
                    label = {
                        Text(text = "Client Name")
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = taxId,
                    onValueChange = {
                        taxId = it
                    },
                    maxLines = 1,
                    label = {
                        Text(text = "Client Tax id")
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                )

                Spacer(modifier = Modifier.height(15.dp))



                Button(onClick = {
                    if (clientName.isNotEmpty() && clientName.isNotBlank() && taxId.isNotEmpty() && taxId.isNotBlank()){

                        rootViewModel.setClientDetails(
                            value = ClientDetails(
                                // ToDo
                                clientId = -1,
                                clientName = clientName,
                            )
                        )
                    }
                    else{
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }
                    onDismissRequest()
                }) {
                    Text(text = "Ok")
                }

            }
        }
    )
}