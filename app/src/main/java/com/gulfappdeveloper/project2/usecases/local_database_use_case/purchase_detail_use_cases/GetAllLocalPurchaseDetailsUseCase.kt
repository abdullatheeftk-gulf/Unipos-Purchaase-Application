package com.gulfappdeveloper.project2.usecases.local_database_use_case.purchase_detail_use_cases

import com.gulfappdeveloper.project2.repositories.LocalDataRepository

class GetAllLocalPurchaseDetailsUseCase(
    private val localDataRepository: LocalDataRepository
) {
    operator fun invoke() = localDataRepository.getAllLocalPurchaseDetails()
}