package com.gulfappdeveloper.project2.domain.services

import com.gulfappdeveloper.project2.domain.models.remote.ClientDetails
import com.gulfappdeveloper.project2.domain.models.remote.GetDataFromRemote
import com.gulfappdeveloper.project2.domain.models.remote.ProductDetails
import kotlinx.coroutines.flow.Flow

interface ApiService {

    suspend fun getClientDetails():Flow<GetDataFromRemote<List<ClientDetails>>>
    suspend fun getProductDetails():Flow<GetDataFromRemote<List<ProductDetails>>>
}