package com.gulfappdeveloper.project2.usecases.data_store_use_cases.ip_address_usecases

import com.gulfappdeveloper.project2.repositories.DataStoreRepository


class SaveIpAddressUseCase(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(ipAddress: String) {
        dataStoreRepository.saveIpAddress(ipAddress = ipAddress)
    }
}