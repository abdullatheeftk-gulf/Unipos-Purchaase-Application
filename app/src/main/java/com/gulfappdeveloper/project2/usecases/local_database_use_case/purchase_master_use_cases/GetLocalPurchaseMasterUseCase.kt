package com.gulfappdeveloper.project2.usecases.local_database_use_case.purchase_master_use_cases

import com.gulfappdeveloper.project2.repositories.LocalDataRepository

class GetLocalPurchaseMasterUseCase(
    private val localDataRepository: LocalDataRepository
) {
    operator fun invoke(userId:Int) = localDataRepository.getLocalPurchaseMaster(userId = userId)
}