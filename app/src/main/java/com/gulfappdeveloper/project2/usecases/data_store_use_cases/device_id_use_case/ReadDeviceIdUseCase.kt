package com.gulfappdeveloper.project2.usecases.data_store_use_cases.device_id_use_case


import com.gulfappdeveloper.project2.repositories.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class ReadDeviceIdUseCase(
    private val dataStoreRepository: DataStoreRepository
) {
    operator fun invoke(): Flow<String>{
        return dataStoreRepository.readDeviceId()
    }
}