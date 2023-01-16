package com.gulfappdeveloper.project2.usecases.remote_usecase.stock_adjustment

import com.gulfappdeveloper.project2.repositories.RemoteRepository

class GetStockOfAProductUseCase(
    private val remoteRepository: RemoteRepository
) {
    suspend operator fun invoke(url: String) =
        remoteRepository.getStockOfAProduct(url = url)
}