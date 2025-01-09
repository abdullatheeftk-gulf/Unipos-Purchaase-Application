package com.gulfappdeveloper.project2.usecases.remote_usecase.get.product

import com.gulfappdeveloper.project2.domain.models.remote.get.GetDataFromRemote
import com.gulfappdeveloper.project2.domain.models.remote.get.Product
import com.gulfappdeveloper.project2.repositories.RemoteRepository
import kotlinx.coroutines.flow.Flow

class GetProductDetailByBarcodeUseCase(
    private val remoteRepository: RemoteRepository
) {
    suspend operator fun invoke(url:String): Flow<GetDataFromRemote<Product?>> {
        return remoteRepository.getProductDetailByBarcode(url = url)
    }
}