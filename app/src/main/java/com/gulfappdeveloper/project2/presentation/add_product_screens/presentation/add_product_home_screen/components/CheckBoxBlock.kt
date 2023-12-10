package com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.add_product_home_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.presentation.ui_util.screenSize

@Composable
fun CheckBoxBlock(
    isInclusive: Boolean,
    isScale: Boolean,
    onSelectedInclusive: (Boolean) -> Unit,
    onSelectedScale: (Boolean) -> Unit
) {
    val screenWidth = screenSize().value

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Is Inclusive Tax",
                color = MaterialTheme.colorScheme.primary,
                fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
            )
            Checkbox(
                checked = isInclusive,
                onCheckedChange = onSelectedInclusive
            )
        }
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Is Scale",
                color = MaterialTheme.colorScheme.primary,
                fontSize = if (screenWidth < 600) 14.sp else if (screenWidth >= 600 && screenWidth < 800) 18.sp else 22.sp,
            )
            Checkbox(
                checked = isScale,
                onCheckedChange = onSelectedScale,
            )
        }


    }
}


