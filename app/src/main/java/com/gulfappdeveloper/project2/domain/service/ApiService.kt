package com.gulfappdeveloper.project2.domain.service

import com.gulfappdeveloper.project2.domain.models.ClientDetails
import com.gulfappdeveloper.project2.domain.models.ProductDetails
import kotlinx.coroutines.flow.Flow

interface ApiService {

    suspend fun getClientDetails():Flow<List<ClientDetails>>
    suspend fun getProductDetails():Flow<List<ProductDetails>>
}