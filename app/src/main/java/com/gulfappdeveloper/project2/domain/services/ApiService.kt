package com.gulfappdeveloper.project2.domain.services

import com.gulfappdeveloper.project2.domain.models.remote.get.ClientDetails
import com.gulfappdeveloper.project2.domain.models.remote.get.GetDataFromRemote
import com.gulfappdeveloper.project2.domain.models.remote.get.Product
import com.gulfappdeveloper.project2.domain.models.remote.get.WelcomeMessage
import com.gulfappdeveloper.project2.domain.models.remote.post.AddClient
import kotlinx.coroutines.flow.Flow

interface ApiService {
    suspend fun getWelcomeMessage(url: String):Flow<GetDataFromRemote<WelcomeMessage>>

    suspend fun getClientDetails(url:String):Flow<GetDataFromRemote<List<ClientDetails>>>
    suspend fun searchClientDetails(url:String):Flow<GetDataFromRemote<List<ClientDetails>>>

    suspend fun getProductDetailsByName(url: String):Flow<GetDataFromRemote<List<Product>>>
    suspend fun getProductDetailByBarcode(url: String):Flow<GetDataFromRemote<Product?>>

    suspend fun addClientDetails(url:String,addClient: AddClient):Flow<GetDataFromRemote<AddClient>>



}