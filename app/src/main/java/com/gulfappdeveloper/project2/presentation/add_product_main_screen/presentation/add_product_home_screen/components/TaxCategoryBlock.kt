package com.gulfappdeveloper.project2.presentation.add_product_main_screen.presentation.add_product_home_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.domain.models.remote.get.for_add_product.TaxCategory
import com.gulfappdeveloper.project2.presentation.add_product_main_screen.AddProductMainViewModel

@Composable
fun TaxCategoryBlock(
    selectedTaxCategory: TaxCategory?,
    showTaxCategoryError: Boolean,
    onSelectedTaxCategory: (TaxCategory) -> Unit,
    addProductMainViewModel: AddProductMainViewModel
) {
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
            fontSize = 18.sp,
            color = MaterialTheme.colors.primary,
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
                        Text(text = "Tax Category")
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
                placeholder = {
                    Text(text = "GST5")
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
                                    })
                                {
                                    Text(text = it.tCategoryName)
                                }
                            }
                        }
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = if (selectedTaxCategory == null) MaterialTheme.colors.onBackground else MaterialTheme.colors.primary,
                    backgroundColor = MaterialTheme.colors.surface,
                    disabledTrailingIconColor = MaterialTheme.colors.error,
                    disabledTextColor = if (selectedTaxCategory == null) MaterialTheme.colors.onBackground else MaterialTheme.colors.primary,
                    disabledLabelColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
                )
            )
            if (showTaxCategoryError) {
                Text(
                    text = "    Tax Category is not selected",
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
        }

    }
}