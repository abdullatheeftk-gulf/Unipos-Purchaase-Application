package com.gulfappdeveloper.project2.usecases.data_store_use_cases.ip_address_usecases

import com.gulfappdeveloper.project2.repositories.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class ReadIpAddressUseCase(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(): Flow<String> {
        return dataStoreRepository.readIpAddress()
    }
}