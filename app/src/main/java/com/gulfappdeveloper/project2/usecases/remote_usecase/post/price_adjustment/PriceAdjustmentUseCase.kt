package com.gulfappdeveloper.project2.usecases.remote_usecase.post.price_adjustment

import com.gulfappdeveloper.project2.domain.models.remote.post.price_adjustment.PriceAdjustment
import com.gulfappdeveloper.project2.repositories.RemoteRepository

class PriceAdjustmentUseCase(
    private val remoteRepository: RemoteRepository
) {
    suspend operator fun invoke(url: String, priceAdjustment: PriceAdjustment) =
        remoteRepository.adjustProductPrice(url = url, priceAdjustment = priceAdjustment)
}