package com.gulfappdeveloper.project2.repositories

import com.gulfappdeveloper.project2.domain.models.remote.ClientDetails
import com.gulfappdeveloper.project2.domain.models.remote.GetDataFromRemote
import com.gulfappdeveloper.project2.domain.models.remote.ProductDetails
import com.gulfappdeveloper.project2.domain.services.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getClientDetails():Flow<GetDataFromRemote<List<ClientDetails>>>{
        return apiService.getClientDetails()
    }

    suspend fun getProductDetails():Flow<GetDataFromRemote<List<ProductDetails>>>{
        return apiService.getProductDetails()
    }

}