package com.gulfappdeveloper.project2.domain.services

import com.gulfappdeveloper.project2.domain.models.remote.get.ClientDetails
import com.gulfappdeveloper.project2.domain.models.remote.get.GetDataFromRemote
import com.gulfappdeveloper.project2.domain.models.remote.get.ProductDetails
import com.gulfappdeveloper.project2.domain.models.remote.get.WelcomeMessage
import kotlinx.coroutines.flow.Flow

interface ApiService {
    suspend fun getWelcomeMessage(url: String):Flow<GetDataFromRemote<WelcomeMessage>>

    suspend fun getClientDetails(url:String):Flow<GetDataFromRemote<List<ClientDetails>>>
    suspend fun searchClientDetails(url:String):Flow<GetDataFromRemote<List<ClientDetails>>>

    suspend fun getProductDetails(url: String):Flow<GetDataFromRemote<List<ProductDetails>>>

}