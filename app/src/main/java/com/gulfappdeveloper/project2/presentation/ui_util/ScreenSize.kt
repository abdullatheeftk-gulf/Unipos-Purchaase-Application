package com.gulfappdeveloper.project2.presentation.ui_util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun screenSize(): Dp {
    val configuration = LocalConfiguration.current
    return configuration.screenWidthDp.dp
}