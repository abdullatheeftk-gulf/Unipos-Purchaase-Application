package com.gulfappdeveloper.project2.presentation.purchase_screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulfappdeveloper.project2.presentation.ui_util.screenSize


@Composable
fun ProductListTitle() {
    val screenWidth = screenSize().value
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Si",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = if (screenWidth < 600) 11.sp else if (screenWidth >= 600 && screenWidth < 800) 13.sp else 16.sp,
                modifier = Modifier.weight(1f),
            )
            Text(
                text = "Product Name",
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                fontSize = if (screenWidth < 600) 11.sp else if (screenWidth >= 600 && screenWidth < 800) 13.sp else 16.sp,
                modifier = Modifier.weight(5.3f),
            )
            Text(
                text = "Qty",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = if (screenWidth < 600) 11.sp else if (screenWidth >= 600 && screenWidth < 800) 13.sp else 16.sp,
                modifier = Modifier.weight(1f),
            )
            Text(
                text = "Unit",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = if (screenWidth < 600) 11.sp else if (screenWidth >= 600 && screenWidth < 800) 13.sp else 16.sp,
                modifier = Modifier.weight(1.4f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Rate",
                textAlign = TextAlign.Center,
                fontSize = if (screenWidth < 600) 11.sp else if (screenWidth >= 600 && screenWidth < 800) 13.sp else 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(2f),
            )
            Text(
                text = "Dis",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = if (screenWidth < 600) 11.sp else if (screenWidth >= 600 && screenWidth < 800) 13.sp else 16.sp,
                modifier = Modifier.weight(1.5f),
            )
            Text(
                text = "Vat%",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = if (screenWidth < 600) 9.sp else if (screenWidth >= 600 && screenWidth < 800) 12.sp else 15.sp,
                modifier = Modifier.weight(1.3f),
            )
            Text(
                text = "Net",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = if (screenWidth < 600) 11.sp else if (screenWidth >= 600 && screenWidth < 800) 13.sp else 16.sp,
                modifier = Modifier.weight(2.5f),
                // textDecoration = TextDecoration.Underline
            )
            Text(
                text = "",
                textAlign = TextAlign.Center,
                fontSize = if (screenWidth < 600) 11.sp else if (screenWidth >= 600 && screenWidth < 800) 13.sp else 16.sp,
                modifier = Modifier.weight(0.75f)
            )

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .padding(horizontal = 4.dp)
                .border(
                    width = 0.25.dp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                )
        ) {}

    }

}



