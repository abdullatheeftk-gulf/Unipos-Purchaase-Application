package com.gulfappdeveloper.project2.usecases.data_store_use_cases.operation_counter_use_cases

import com.gulfappdeveloper.project2.repositories.DataStoreRepository

class UpdateOperationCountUseCase(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(){
        dataStoreRepository.updateOperationCount()
    }
}