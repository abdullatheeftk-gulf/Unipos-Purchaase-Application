package com.gulfappdeveloper.project2.presentation.main_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gulfappdeveloper.project2.BuildConfig
import com.gulfappdeveloper.project2.navigation.root.RootNavScreens
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.ui_util.screenSize

@Composable
fun MainScreen(
    rootViewModel: RootViewModel,
    navHostController: NavHostController
) {
    val screenWidth = screenSize().value

    val snackBarHostState = remember {
        SnackbarHostState()
    }

    val uniLicenseDetails by rootViewModel.uniLicenseDetails

    LaunchedEffect(key1 = true) {
        rootViewModel.saveBasicData(screenSize = "width:- ${screenWidth.dp.value}")
    }


    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
    ) {
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
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF6FACC7)
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "PURCHASE",
                            color = Color.Black,
                            fontSize = if(screenWidth<500f) 16.sp else if(screenWidth>=500f && screenWidth<900f) 18.sp else 22.sp,
                            fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
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
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFEDCBC0)
                    ),
                    shape = RoundedCornerShape(25)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "STOCK ADJUSTMENT",
                            color = Color.Black,
                            fontSize = if(screenWidth<500f) 16.sp else if(screenWidth>=500f && screenWidth<900f) 18.sp else 22.sp,
                            fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
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
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFBFA9CD)
                    ),
                    shape = RoundedCornerShape(25)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if(screenWidth<500f) "ADD\nPRODUCT" else "ADD PRODUCT",
                            color = Color.Black,
                            fontSize = if(screenWidth<500f) 16.sp else if(screenWidth>=500f && screenWidth<900f) 18.sp else 22.sp,
                            fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
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
                            navHostController.navigate(route = RootNavScreens.ShowProductForPriceAdjustmentScreen.route)
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE4C08C)
                    ),
                    shape = RoundedCornerShape(25)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "PRICE ADJUSTMENT",
                            color = Color.Black,
                            fontSize = if(screenWidth<500f) 16.sp else if(screenWidth>=500f && screenWidth<900f) 18.sp else 22.sp,
                            fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
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
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFA0CAA1)
                    ),
                    shape = RoundedCornerShape(25)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if(screenWidth<600f) "ADD\nCLIENT" else "ADD CLIENT",
                            color = Color.Black,
                            fontSize = if(screenWidth<500f) 16.sp else if(screenWidth>=500f && screenWidth<900f) 18.sp else 22.sp,
                            fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
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
                            navHostController.navigate(RootNavScreens.PrintBarcodeScreen.route)
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xD0BDBDCD)
                    ),
                    shape = RoundedCornerShape(25)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if(screenWidth<=500f) "PRINT\nBARCODE" else "PRINT BARCODE" ,
                            color = Color.Black,
                            fontSize = if(screenWidth<500f) 16.sp else if(screenWidth>=500f && screenWidth<900f) 18.sp else 22.sp,
                            fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
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
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFCDA9B5)
                    ),
                    shape = RoundedCornerShape(25)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "SETTINGS",
                            color = Color.Black,
                            fontSize = if(screenWidth<500f) 16.sp else if(screenWidth>=500f && screenWidth<900f) 18.sp else 22.sp,
                            fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
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
                    color = MaterialTheme.colorScheme.error,
                    fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
                    fontSize = 20.sp,
                    textDecoration = TextDecoration.Underline
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Valid until :- ${uniLicenseDetails?.expiryDate}")
                Spacer(modifier = Modifier.height(30.dp))
            }
        }

    }

}


