package com.gulfappdeveloper.project2.usecases.remote_usecase.stock_adjustment

import com.gulfappdeveloper.project2.domain.models.remote.post.stoke_adjustment.StockAdjustment
import com.gulfappdeveloper.project2.repositories.RemoteRepository

class AdjustStocksOfProductListUseCase(
    private val remoteRepository: RemoteRepository
) {
    suspend operator fun invoke(url:String,stockAdjustment: StockAdjustment) =
        remoteRepository.adjustStocksOfProductList(
            url = url,
            stockAdjustment = stockAdjustment
        )
}