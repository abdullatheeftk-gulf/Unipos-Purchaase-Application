package com.gulfappdeveloper.project2.usecases

import com.gulfappdeveloper.project2.usecases.data_store_use_cases.base_url_use_cases.ReadBaseUrlUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.base_url_use_cases.SaveBaseUrlUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.operation_counter_use_cases.ReadOperationCountUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.operation_counter_use_cases.UpdateOperationCountUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.client.GetClientDetailsUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.client.SearchClientDetailsUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.product.GetProductDetailByBarcodeUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.product.GetProductDetailsByNameUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.welcome.GetWelcomeMessageUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.post.add_client.AddClientUseCase

data class UseCase(
    val getWelcomeMessageUseCase: GetWelcomeMessageUseCase,

    val getClientDetailsUseCase: GetClientDetailsUseCase,
    val searchClientDetailsUseCase: SearchClientDetailsUseCase,

    val getProductDetailsUseCase: GetProductDetailsByNameUseCase,
    val getProductDetailByBarcodeUseCase: GetProductDetailByBarcodeUseCase,

    // post
    val addClientUseCase: AddClientUseCase,

    val updateOperationCountUseCase: UpdateOperationCountUseCase,
    val saveBaseUrlUseCase: SaveBaseUrlUseCase,

    val readOperationCountUseCase: ReadOperationCountUseCase,
    val readBaseUrlUseCase: ReadBaseUrlUseCase,


)