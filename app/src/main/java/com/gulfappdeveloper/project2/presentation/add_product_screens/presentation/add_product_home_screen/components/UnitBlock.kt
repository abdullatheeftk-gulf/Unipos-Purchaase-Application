package com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.add_product_home_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.domain.models.remote.get.for_add_product.Units
import com.gulfappdeveloper.project2.presentation.add_product_screens.AddProductMainViewModel

@Composable
fun UnitBlock(
    selectedUnits: Units?,
    onUnitsSelected: (Units) -> Unit,
    addProductMainViewModel: AddProductMainViewModel,
    showUnitError: Boolean
) {
    var showDropDownMenuForUnits by remember {
        mutableStateOf(false)
    }



    val unitsList = addProductMainViewModel.unitsList

    Spacer(modifier = Modifier.height(20.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Select Unit :-",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.weight(1f)
        )

        Column(modifier = Modifier.weight(1f)) {
            OutlinedTextField(
                value = selectedUnits?.unitName ?: "",
                textStyle = TextStyle(
                    color = if (selectedUnits != null) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
                ),
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Pcs")
                },
                label = {
                    Row() {
                        Text(text = "Unit")
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
                trailingIcon = {
                    IconButton(onClick = {
                        showDropDownMenuForUnits = true
                    }) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = null
                        )
                        DropdownMenu(
                            expanded = showDropDownMenuForUnits,
                            onDismissRequest = {
                                showDropDownMenuForUnits = false
                            }
                        )
                        {
                            unitsList.forEach {
                                DropdownMenuItem(
                                    onClick = {
                                        onUnitsSelected(it)
                                        showDropDownMenuForUnits = false
                                    },
                                    text = {
                                        Text(text = it.unitName)
                                    }
                                    )
                            }
                        }
                    }
                },
                enabled = false,
                colors = OutlinedTextFieldDefaults.colors(
                    disabledBorderColor = MaterialTheme.colorScheme.onSurface,
                    disabledTrailingIconColor = MaterialTheme.colorScheme.error,
                    disabledTextColor = if (selectedUnits == null) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.primary,
                    disabledLabelColor = MaterialTheme.colorScheme.onSurface
                )
            )

            if (showUnitError) {
                Text(
                    text = "    Unit is not selected",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

        }


    }

}