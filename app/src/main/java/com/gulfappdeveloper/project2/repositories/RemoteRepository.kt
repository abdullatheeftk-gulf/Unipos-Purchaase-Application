package com.gulfappdeveloper.project2.repositories

import com.gulfappdeveloper.project2.domain.models.ClientDetails
import com.gulfappdeveloper.project2.domain.models.ProductDetails
import com.gulfappdeveloper.project2.domain.service.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getClientDetails():Flow<List<ClientDetails>>{
        return apiService.getClientDetails()
    }

    suspend fun getProductDetails():Flow<List<ProductDetails>>{
        return apiService.getProductDetails()
    }

}