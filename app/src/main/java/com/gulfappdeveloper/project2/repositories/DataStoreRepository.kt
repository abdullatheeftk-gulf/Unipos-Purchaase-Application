package com.gulfappdeveloper.project2.repositories

import com.gulfappdeveloper.project2.domain.services.DataStoreService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreRepository @Inject constructor(
    private val dataStoreService: DataStoreService
) {

    suspend fun updateOperationCount() {
        dataStoreService.updateOperationCount()
    }

    suspend fun saveBaseUrl(baseUrl: String) {
        dataStoreService.saveBaseUrl(baseUrl = baseUrl)
    }


    fun readOperationCount(): Flow<Int> {
        return dataStoreService.readOperationCount()
    }

    fun readBaseUrl(): Flow<String> {
        return dataStoreService.readBaseUrl()
    }


}