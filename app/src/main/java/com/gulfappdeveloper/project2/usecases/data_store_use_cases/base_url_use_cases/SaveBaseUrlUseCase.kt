package com.gulfappdeveloper.project2.usecases.data_store_use_cases.base_url_use_cases

import com.gulfappdeveloper.project2.repositories.DataStoreRepository

class SaveBaseUrlUseCase(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(baseUrl:String){
        dataStoreRepository.saveBaseUrl(baseUrl = baseUrl)
    }
}