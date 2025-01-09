package com.gulfappdeveloper.project2.usecases.data_store_use_cases.serial_counter_usecases

import com.gulfappdeveloper.project2.repositories.DataStoreRepository


class UpdateSerialNoUseCase(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke() {
        dataStoreRepository.updateSerialNo()
    }
}