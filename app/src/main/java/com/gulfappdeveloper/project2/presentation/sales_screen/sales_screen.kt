package com.gulfappdeveloper.project2.presentation.sales_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun SalesScreen(
    modifier: Modifier = Modifier,
    salesScreenViewModel: SalesScreenViewModel = hiltViewModel() ,
    hideKeyboard: () -> Unit,
    navHostController: NavHostController,
) {

    val message by salesScreenViewModel.message
    
    Scaffold { 
        it.calculateTopPadding()
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(message)
        }
    }
}