package com.gulfappdeveloper.project2.usecases.data_store_use_cases.serial_counter_usecases

import com.gulfappdeveloper.project2.repositories.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class ReadSerialNoCountUseCase(
    private val dataStoreRepository: DataStoreRepository
) {
    operator fun invoke(): Flow<Int> {
        return dataStoreRepository.readSerialNo()
    }
}