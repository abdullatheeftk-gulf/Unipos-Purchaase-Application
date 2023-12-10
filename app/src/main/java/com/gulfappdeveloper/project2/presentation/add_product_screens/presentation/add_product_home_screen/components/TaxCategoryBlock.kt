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
import com.gulfappdeveloper.project2.domain.models.remote.get.for_add_product.TaxCategory
import com.gulfappdeveloper.project2.presentation.add_product_screens.AddProductMainViewModel
import com.gulfappdeveloper.project2.presentation.ui_util.screenSize

@Composable
fun TaxCategoryBlock(
    selectedTaxCategory: TaxCategory?,
    showTaxCategoryError: Boolean,
    onSelectedTaxCategory: (TaxCategory) -> Unit,
    addProductMainViewModel: AddProductMainViewModel
) {

    val screenWidth = screenSize().value

    var showDropDownMenuForTaxCategory by remember {
        mutableStateOf(false)
    }

    val taxCategories = addProductMainViewModel.taxCategoryList

    Spacer(modifier = Modifier.height(20.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Select Tax Category :-",
            fontSize = if (screenWidth < 600) 18.sp else if (screenWidth >= 600 && screenWidth < 800) 22.sp else 24.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.weight(1f)
        )

        Column(modifier = Modifier.weight(1f)) {
            OutlinedTextField(
                value = selectedTaxCategory?.tCategoryName ?: "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                enabled = false,
                label = {
                    Row() {
                        Text(
                            text = "Tax Category",
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
                placeholder = {
                    Text(
                        text = "GST5",
                        fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            showDropDownMenuForTaxCategory = true
                        },
                        enabled = true
                    ) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = null
                        )
                        DropdownMenu(
                            expanded = showDropDownMenuForTaxCategory,
                            onDismissRequest = {
                                showDropDownMenuForTaxCategory = false
                            }
                        )
                        {
                            taxCategories.forEach {
                                DropdownMenuItem(
                                    onClick = {
                                        onSelectedTaxCategory(it)
                                        showDropDownMenuForTaxCategory = false
                                    },
                                    text = {Text(
                                        text = it.tCategoryName,
                                        fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                                    )}
                                )
                            }
                        }
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    disabledBorderColor = MaterialTheme.colorScheme.onSurface,
                    disabledTrailingIconColor = MaterialTheme.colorScheme.error,
                    disabledTextColor = if (selectedTaxCategory == null) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.primary,
                    disabledLabelColor = MaterialTheme.colorScheme.onSurface
                ),
                textStyle = TextStyle(
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                )
            )
            if (showTaxCategoryError) {
                Text(
                    text = "    Tax Category is not selected",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Start),
                    fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
                )
            }
        }

    }
}