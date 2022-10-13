package com.gulfappdeveloper.project2.usecases.data_store_use_cases.base_url_use_cases

import com.gulfappdeveloper.project2.repositories.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class ReadBaseUrlUseCase(
    private val dataStoreRepository: DataStoreRepository
) {
    operator fun invoke():Flow<String>{
        return dataStoreRepository.readBaseUrl()
    }
}