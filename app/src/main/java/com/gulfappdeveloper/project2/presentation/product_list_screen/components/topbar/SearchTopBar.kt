package com.gulfappdeveloper.project2.presentation.product_list_screen.components.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.navigation.root.RootViewModel2

@Composable
fun SearchTopBar(
    rootViewModel: RootViewModel,
    rootViewModel2: RootViewModel2,
    onClearButtonClicked: () -> Unit,
    onBackButtonClicked: () -> Unit,
    hideKeyboard: () -> Unit,
) {
    val productSearchText by rootViewModel.productSearchText
    TopAppBar {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackButtonClicked) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
            BasicTextField(
                value = productSearchText,
                onValueChange = { value ->
                    rootViewModel2.setProductSearchText(value = value)
                },
                keyboardActions = KeyboardActions(
                    onSearch = {
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
                        contentAlignment = Alignment.CenterStart
                    ) {

                        if (productSearchText.isEmpty()) {
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
                    .background(color = Color.White),
                textStyle = TextStyle(
                    fontSize = 20.sp,
                ),
                singleLine = true

            )

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


    }
}