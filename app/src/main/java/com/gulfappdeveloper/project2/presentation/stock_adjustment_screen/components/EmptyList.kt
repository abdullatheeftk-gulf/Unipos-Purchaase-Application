package com.gulfappdeveloper.project2.presentation.stock_adjustment_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.presentation.ui_util.screenSize

@Composable
fun EmptyList() {
    val screenWidth = screenSize().value
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Empty List",
            fontWeight = FontWeight.W600,
            fontSize = if(screenWidth<600f) 24.sp else if(screenWidth>=600 && screenWidth<800) 30.sp else 32.sp,
            modifier = Modifier.alpha(0.3f)
        )
    }
}