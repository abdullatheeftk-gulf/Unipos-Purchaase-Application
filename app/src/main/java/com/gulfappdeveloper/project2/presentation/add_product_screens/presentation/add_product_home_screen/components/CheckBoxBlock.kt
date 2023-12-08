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

@Composable
fun CheckBoxBlock(
    isInclusive: Boolean,
    isScale: Boolean,
    onSelectedInclusive: (Boolean) -> Unit,
    onSelectedScale: (Boolean) -> Unit
) {

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
                color = MaterialTheme.colorScheme.primary
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
                color = MaterialTheme.colorScheme.primary
            )
            Checkbox(
                checked = isScale,
                onCheckedChange = onSelectedScale,
                )
        }


    }
}


