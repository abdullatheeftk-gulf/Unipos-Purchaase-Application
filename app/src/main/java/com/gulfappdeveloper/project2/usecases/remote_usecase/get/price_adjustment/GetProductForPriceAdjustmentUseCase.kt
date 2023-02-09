package com.gulfappdeveloper.project2.usecases.remote_usecase.get.price_adjustment

import com.gulfappdeveloper.project2.repositories.RemoteRepository

class GetProductForPriceAdjustmentUseCase(
    private val remoteRepository: RemoteRepository
) {
    suspend operator fun invoke(url:String) = remoteRepository.getProductForPriceAdjustment(url = url)
}