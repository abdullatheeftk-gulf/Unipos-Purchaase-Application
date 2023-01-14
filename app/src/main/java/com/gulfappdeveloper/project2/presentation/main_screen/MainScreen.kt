package com.gulfappdeveloper.project2.presentation.main_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.navigation.root.RootNavScreens
import com.gulfappdeveloper.project2.navigation.root.RootViewModel

@Composable
fun MainScreen(
    rootViewModel: RootViewModel,
    navHostController: NavHostController
) {
    val scaffoldState = rememberScaffoldState()


    Scaffold(scaffoldState = scaffoldState) {
        it.calculateTopPadding()
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Card(
                modifier = Modifier
                    .size(width = 250.dp, height = 250.dp)
                    .padding(24.dp)
                    .clip(shape = RoundedCornerShape(25))
                    .clickable {
                        navHostController.navigate(route = RootNavScreens.HomeScreen.route)
                    },
                backgroundColor = Color(0xFF6FACC7),
               // shape = RoundedCornerShape(25)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "PURCHASE",
                        color = Color.Black,
                        fontSize = MaterialTheme.typography.h6.fontSize,
                        fontStyle = MaterialTheme.typography.h6.fontStyle,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Card(
                modifier = Modifier
                    .size(width = 250.dp, height = 250.dp)
                    .padding(24.dp)
                    .clip(shape = RoundedCornerShape(25))
                    .clickable {

                    }, backgroundColor = Color(0xFFEDCBC0), shape = RoundedCornerShape(25)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "STOCK\n ADJUSTMENT",
                        color = Color.Black,
                        fontSize = MaterialTheme.typography.h6.fontSize,
                        fontStyle = MaterialTheme.typography.h6.fontStyle,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Card(
                modifier = Modifier
                    .size(width = 250.dp, height = 250.dp)
                    .padding(24.dp)
                    .clip(shape = RoundedCornerShape(25))
                    .clickable {

                    }, backgroundColor = Color(0xFFCDA9B5), shape = RoundedCornerShape(25)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "SETTINGS",
                        color = Color.Black,
                        fontSize = MaterialTheme.typography.h6.fontSize,
                        fontStyle = MaterialTheme.typography.h6.fontStyle,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }


    }

}

/*
@Preview
@Composable
fun MainScreenPrev() {
    MainScreen()
}*/
