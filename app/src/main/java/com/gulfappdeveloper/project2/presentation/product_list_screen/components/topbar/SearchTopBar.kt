package com.gulfappdeveloper.project2.presentation.product_list_screen.components.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
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
import com.gulfappdeveloper.project2.navigation.root.RootViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    rootViewModel: RootViewModel,
    onClearButtonClicked: () -> Unit,
    onBackButtonClicked: () -> Unit,
    hideKeyboard: () -> Unit,
    onSearchTextIsLessThanThree: () -> Unit
) {
    val productSearchText by rootViewModel.productName
    TopAppBar(
        modifier = Modifier.shadow(elevation = 6.dp),
        title = {
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
                        rootViewModel.setProductName(
                            value = value,
                            isItFromHomeScreen = false,
                            requiredSearch = false
                        )
                    },
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            hideKeyboard()
                            if (productSearchText.length < 3) {
                                onSearchTextIsLessThanThree()
                            }
                            rootViewModel.setProductName(
                                value = productSearchText,
                                isItFromHomeScreen = false,
                                requiredSearch = true
                            )
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
                        .clip(MaterialTheme.shapes.medium.copy(all = CornerSize(16.dp)))
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


}
