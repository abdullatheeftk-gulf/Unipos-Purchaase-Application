package com.gulfappdeveloper.project2.usecases.remote_usecases

import com.gulfappdeveloper.project2.domain.models.ProductDetails
import com.gulfappdeveloper.project2.repositories.RemoteRepository
import kotlinx.coroutines.flow.Flow

class GetProductDetailsUseCase(
    private val remoteRepository: RemoteRepository
) {
    suspend operator fun invoke():Flow<List<ProductDetails>>{
        return remoteRepository.getProductDetails()
    }
}