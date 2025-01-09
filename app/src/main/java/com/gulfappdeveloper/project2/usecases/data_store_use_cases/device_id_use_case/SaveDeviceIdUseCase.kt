package com.gulfappdeveloper.project2.usecases.data_store_use_cases.device_id_use_case

import com.gulfappdeveloper.project2.repositories.DataStoreRepository


class SaveDeviceIdUseCase(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(deviceId:String){
        dataStoreRepository.saveDeviceId(deviceId = deviceId)
    }
}