package com.gulfappdeveloper.project2.usecases.data_store_use_cases.uni_license_save_use_cases

import com.gulfappdeveloper.project2.repositories.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class UniLicenseReadUseCase(
    private val dataStoreRepository: DataStoreRepository
) {

    operator fun invoke(): Flow<String> {
        return dataStoreRepository.readUniLicenseKey()
    }
}