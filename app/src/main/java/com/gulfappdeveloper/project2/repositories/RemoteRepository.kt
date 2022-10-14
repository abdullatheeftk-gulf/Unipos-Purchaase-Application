package com.gulfappdeveloper.project2.repositories

import com.gulfappdeveloper.project2.domain.models.remote.get.ClientDetails
import com.gulfappdeveloper.project2.domain.models.remote.get.GetDataFromRemote
import com.gulfappdeveloper.project2.domain.models.remote.get.ProductDetails
import com.gulfappdeveloper.project2.domain.models.remote.get.WelcomeMessage
import com.gulfappdeveloper.project2.domain.services.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getWelcomeMessage(url: String):Flow<GetDataFromRemote<WelcomeMessage>>{
        return apiService.getWelcomeMessage(url = url)
    }

    suspend fun getClientDetails(url:String):Flow<GetDataFromRemote<List<ClientDetails>>>{
        return apiService.getClientDetails(url = url)
    }

    suspend fun getProductDetails(url:String):Flow<GetDataFromRemote<List<ProductDetails>>>{
        return apiService.getProductDetails(url = url)
    }

}