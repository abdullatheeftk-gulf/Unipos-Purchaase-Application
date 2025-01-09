package com.gulfappdeveloper.project2.usecases.remote_usecase.post.purchase

import com.gulfappdeveloper.project2.domain.models.remote.get.GetDataFromRemote
import com.gulfappdeveloper.project2.domain.models.remote.post.PurchaseClass
import com.gulfappdeveloper.project2.repositories.RemoteRepository
import kotlinx.coroutines.flow.Flow

class PurchaseUseCase(
    private val remoteRepository: RemoteRepository
) {
    suspend operator fun invoke(url: String, purchaseClass: PurchaseClass):Flow<GetDataFromRemote<PurchaseClass>> =
        remoteRepository.purchaseFunction(url = url, purchaseClass = purchaseClass)
}