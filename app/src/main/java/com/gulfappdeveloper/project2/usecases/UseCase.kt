package com.gulfappdeveloper.project2.usecases

import com.gulfappdeveloper.project2.usecases.data_store_use_cases.base_url_use_cases.ReadBaseUrlUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.base_url_use_cases.SaveBaseUrlUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.operation_counter_use_cases.ReadOperationCountUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.operation_counter_use_cases.UpdateOperationCountUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecases.GetClientDetailsUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecases.GetProductDetailsUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecases.GetWelcomeMessageUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecases.client.SearchClientDetailsUseCase

data class UseCase(
    val getWelcomeMessageUseCase: GetWelcomeMessageUseCase,

    val getClientDetailsUseCase: GetClientDetailsUseCase,
    val searchClentDetailsUseCase:SearchClientDetailsUseCase,

    val getProductDetailsUseCase: GetProductDetailsUseCase,




    val updateOperationCountUseCase: UpdateOperationCountUseCase,
    val saveBaseUrlUseCase: SaveBaseUrlUseCase,

    val readOperationCountUseCase: ReadOperationCountUseCase,
    val readBaseUrlUseCase: ReadBaseUrlUseCase
)