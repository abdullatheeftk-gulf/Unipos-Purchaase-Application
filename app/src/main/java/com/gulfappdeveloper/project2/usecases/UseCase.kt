package com.gulfappdeveloper.project2.usecases

import com.gulfappdeveloper.project2.usecases.remote_usecases.GetClientDetailsUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecases.GetProductDetailsUseCase

data class UseCase(
    val getClientDetailsUseCase: GetClientDetailsUseCase,
    val getProductDetailsUseCase: GetProductDetailsUseCase
)