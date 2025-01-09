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
import com.gulfappdeveloper.project2.presentation.ui_util.screenSize

@Composable
fun UnitBlock(
    selectedUnits: Units?,
    onUnitsSelected: (Units) -> Unit,
    addProductMainViewModel: AddProductMainViewModel,
    showUnitError: Boolean
) {

    val screenWidth = screenSize().value

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
            fontSize = if (screenWidth < 600) 18.sp else if (screenWidth >= 600 && screenWidth < 800) 22.sp else 26.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.weight(1f)
        )

        Column(modifier = Modifier.weight(1f)) {
            OutlinedTextField(
                value = selectedUnits?.unitName ?: "",
                textStyle = TextStyle(
                    color = if (selectedUnits != null) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                ),
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Pcs",
                        fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                    )
                },
                label = {
                    Row() {
                        Text(
                            text = "Unit",
                            fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                        )
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
                            fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
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
                                        Text(
                                            text = it.unitName,
                                            fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                                        )
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
                ),

                )

            if (showUnitError) {
                Text(
                    text = "    Unit is not selected",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Start),
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                )
            }

        }


    }

}