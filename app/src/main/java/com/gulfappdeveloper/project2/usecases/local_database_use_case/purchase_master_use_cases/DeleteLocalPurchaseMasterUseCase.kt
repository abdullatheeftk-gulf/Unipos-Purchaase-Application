package com.gulfappdeveloper.project2.usecases.local_database_use_case.purchase_master_use_cases

import com.gulfappdeveloper.project2.domain.models.room.LocalPurchaseMaster
import com.gulfappdeveloper.project2.repositories.LocalDataRepository

class DeleteLocalPurchaseMasterUseCase(
    private val localDataRepository: LocalDataRepository
) {

    suspend operator fun invoke() =
        localDataRepository.deleteLocalPurchaseMaster()
}