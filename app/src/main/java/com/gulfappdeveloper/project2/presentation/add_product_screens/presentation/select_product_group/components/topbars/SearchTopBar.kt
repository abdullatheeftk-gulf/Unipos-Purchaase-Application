package com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.select_product_group.components.topbars

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.presentation.add_product_screens.AddProductMainViewModel
import com.gulfappdeveloper.project2.ui.theme.OrangeColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    onClearButtonClicked: () -> Unit,
    hideKeyboard: () -> Unit,
    addProductViewModel: AddProductMainViewModel,
) {

    val searchText by addProductViewModel.searchText

    TopAppBar(
        modifier = Modifier.shadow(elevation = 6.dp),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = searchText,
                    onValueChange = addProductViewModel::setSearchText,
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            addProductViewModel.searchProductGroups()
                            hideKeyboard()
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search,
                        keyboardType = KeyboardType.Text
                    ),
                    decorationBox = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            contentAlignment = Alignment.CenterStart,

                            ) {
                            if (searchText.isEmpty()) {
                                Text(
                                    text = "Search",
                                    color = Color.DarkGray,
                                    modifier = Modifier
                                        .alpha(0.3f)
                                )
                            }
                            it()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .weight(1f)
                        .padding(all = 4.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .background(color = Color.White),
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                    ),
                    singleLine = true

                )
                IconButton(onClick = {
                    addProductViewModel.searchProductGroups()
                    hideKeyboard()
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = OrangeColor
                    )
                }
                IconButton(onClick = {
                    onClearButtonClicked()
                    hideKeyboard()
                }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        })

    /*TopAppBar(backgroundColor = MaterialTheme.colors.surface) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = searchText,
                onValueChange = addProductViewModel::setSearchText,
                keyboardActions = KeyboardActions(
                    onSearch = {
                        addProductViewModel.searchProductGroups()
                        hideKeyboard()
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search,
                    keyboardType = KeyboardType.Text
                ),
                decorationBox = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        contentAlignment = Alignment.CenterStart,

                    ) {
                        if (searchText.isEmpty()) {
                            Text(
                                text = "Search",
                                color = Color.DarkGray,
                                modifier = Modifier
                                    .alpha(ContentAlpha.disabled)
                            )
                        }
                        it()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .weight(1f)
                    .padding(all = 4.dp)
                    .clip(MaterialTheme.shapes.medium.copy(all = CornerSize(16.dp)))
                    .border(width = 1.dp, color = MaterialTheme.colors.primary, shape = RoundedCornerShape(16.dp))
                    .background(color = Color.White)
                    ,
                textStyle = TextStyle(
                    fontSize = 20.sp,
                ),
                singleLine = true

            )
            IconButton(onClick = {
                addProductViewModel.searchProductGroups()
                hideKeyboard()
            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colors.OrangeColor
                )
            }
            IconButton(onClick = {
                onClearButtonClicked()
                hideKeyboard()
            }) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    tint = MaterialTheme.colors.error
                )
            }
        }


    }*/
}