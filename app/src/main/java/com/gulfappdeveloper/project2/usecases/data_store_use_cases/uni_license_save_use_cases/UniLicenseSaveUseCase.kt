package com.gulfappdeveloper.project2.usecases.data_store_use_cases.uni_license_save_use_cases

import com.gulfappdeveloper.project2.repositories.DataStoreRepository

class UniLicenseSaveUseCase(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(uniLicenseString: String) {
        dataStoreRepository.saveUniLicenseKey(uniLicenseString = uniLicenseString)
    }
}