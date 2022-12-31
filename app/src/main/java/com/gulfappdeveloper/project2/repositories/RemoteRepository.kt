package com.gulfappdeveloper.project2.repositories

import com.gulfappdeveloper.project2.domain.models.remote.get.ClientDetails
import com.gulfappdeveloper.project2.domain.models.remote.get.GetDataFromRemote
import com.gulfappdeveloper.project2.domain.models.remote.get.Product
import com.gulfappdeveloper.project2.domain.models.remote.get.WelcomeMessage
import com.gulfappdeveloper.project2.domain.models.remote.post.AddClient
import com.gulfappdeveloper.project2.domain.services.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getWelcomeMessage(url: String): Flow<GetDataFromRemote<WelcomeMessage>> {
        return apiService.getWelcomeMessage(url = url)
    }

    suspend fun getClientDetails(url: String): Flow<GetDataFromRemote<List<ClientDetails>>> {
        return apiService.getClientDetails(url = url)
    }

    suspend fun searchClientDetails(url: String): Flow<GetDataFromRemote<List<ClientDetails>>> {
        return apiService.searchClientDetails(url = url)
    }

    suspend fun getProductDetailsByName(url: String): Flow<GetDataFromRemote<List<Product>>> {
        return apiService.getProductDetailsByName(url = url)
    }

    suspend fun getProductDetailByBarcode(url: String): Flow<GetDataFromRemote<Product?>> {
        return apiService.getProductDetailByBarcode(url = url)
    }


    // Post remote repositories
    suspend fun addClientDetails(
        url: String,
        addClient: AddClient
    ): Flow<GetDataFromRemote<AddClient>> {
        return apiService.addClientDetails(url = url, addClient = addClient)
    }
}