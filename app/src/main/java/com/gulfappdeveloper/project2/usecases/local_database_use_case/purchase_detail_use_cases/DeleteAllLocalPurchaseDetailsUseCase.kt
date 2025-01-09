package com.gulfappdeveloper.project2.usecases.local_database_use_case.purchase_detail_use_cases

import com.gulfappdeveloper.project2.domain.models.room.LocalPurchaseDetail
import com.gulfappdeveloper.project2.repositories.LocalDataRepository

class DeleteAllLocalPurchaseDetailsUseCase(
    private val localDataRepository: LocalDataRepository
) {

    suspend operator fun invoke() =
        localDataRepository.deleteAllLocalPurchaseDetails()
}