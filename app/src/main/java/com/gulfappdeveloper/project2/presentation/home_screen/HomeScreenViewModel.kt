package com.gulfappdeveloper.project2.presentation.home_screen

import androidx.lifecycle.ViewModel
import com.gulfappdeveloper.project2.usecases.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "HomeScreenViewModel"

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {


}