package com.gulfappdeveloper.project2.presentation.main_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.BuildConfig
import com.gulfappdeveloper.project2.navigation.root.RootNavScreens
import com.gulfappdeveloper.project2.navigation.root.RootViewModel

@Composable
fun MainScreen(
    rootViewModel: RootViewModel,
    navHostController: NavHostController
) {
    val scaffoldState = rememberScaffoldState()

    val uniLicenseDetails by rootViewModel.uniLicenseDetails


    Scaffold(scaffoldState = scaffoldState) {
        it.calculateTopPadding()

        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            item {
                Card(
                    modifier = Modifier
                        .size(width = 150.dp, height = 150.dp)
                        .padding(24.dp)
                        .clip(shape = RoundedCornerShape(25))
                        .clickable {
                            navHostController.navigate(route = RootNavScreens.PurchaseScreen.route)
                        },
                    backgroundColor = Color(0xFF6FACC7),
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
            }

            item {
                Card(
                    modifier = Modifier
                        .size(width = 150.dp, height = 150.dp)
                        .padding(24.dp)
                        .clip(shape = RoundedCornerShape(25))
                        .clickable {
                            navHostController.navigate(route = RootNavScreens.StockAdjustmentScreen.route)
                        },
                    backgroundColor = Color(0xFFEDCBC0),
                    shape = RoundedCornerShape(25)
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
            }

            item {
                Card(
                    modifier = Modifier
                        .size(width = 150.dp, height = 150.dp)
                        .padding(24.dp)
                        .clip(shape = RoundedCornerShape(25))
                        .clickable {
                            //true is for from the Main screen
                            rootViewModel.setNavFrom(true)
                            navHostController.navigate(route = RootNavScreens.AddProductMainScreen.route)
                        },
                    backgroundColor = Color(0xFFBFA9CD),
                    shape = RoundedCornerShape(25)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "ADD\nPRODUCT",
                            color = Color.Black,
                            fontSize = MaterialTheme.typography.h6.fontSize,
                            fontStyle = MaterialTheme.typography.h6.fontStyle,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier
                        .size(width = 150.dp, height = 150.dp)
                        .padding(24.dp)
                        .clip(shape = RoundedCornerShape(25))
                        .clickable {
                            rootViewModel.setAddClientScreenNavPopUpFlag(false)
                            navHostController.navigate(RootNavScreens.AddClientScreen.route)
                        },
                    backgroundColor = Color(0xFFA0CAA1),
                    shape = RoundedCornerShape(25)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "ADD\nCLIENT",
                            color = Color.Black,
                            fontSize = MaterialTheme.typography.h6.fontSize,
                            fontStyle = MaterialTheme.typography.h6.fontStyle,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier
                        .size(width = 150.dp, height = 150.dp)
                        .padding(24.dp)
                        .clip(shape = RoundedCornerShape(25))
                        .clickable {
                            navHostController.navigate(route = RootNavScreens.SettingsScreen.route)
                        }, backgroundColor = Color(0xFFCDA9B5),
                    shape = RoundedCornerShape(25)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
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

            item {
                Card(
                    modifier = Modifier
                        .size(width = 150.dp, height = 150.dp)
                        .padding(24.dp)
                        .clip(shape = RoundedCornerShape(25))
                        .clickable {
                            navHostController.popBackStack()
                            navHostController.navigate(route = RootNavScreens.LoginScreen.route)
                        },
                    backgroundColor = Color(0xFFDE8989),
                    shape = RoundedCornerShape(25)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "LOGOUT",
                            color = Color.Black,
                            fontSize = MaterialTheme.typography.h6.fontSize,
                            fontStyle = MaterialTheme.typography.h6.fontStyle,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
        if (uniLicenseDetails?.licenseType != "permanent") {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {

                Text(
                    text = if (BuildConfig.DEBUG) "Debug Version" else "Demo version",
                    color = MaterialTheme.colors.error,
                    fontStyle = MaterialTheme.typography.h6.fontStyle,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    textDecoration = TextDecoration.Underline
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Valid until :- ${uniLicenseDetails?.expiryDate}")
                Spacer(modifier = Modifier.height(30.dp))
            }
        }

    }

}


