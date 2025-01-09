package com.gulfappdeveloper.project2.usecases.data_store_use_cases.operation_counter_use_cases

import com.gulfappdeveloper.project2.repositories.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class ReadOperationCountUseCase(
    private val dataStoreRepository: DataStoreRepository
) {
    operator fun invoke():Flow<Int>{
        return dataStoreRepository.readOperationCount()
    }
}