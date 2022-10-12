package com.gulfappdeveloper.project2.usecases.remote_usecases

import com.gulfappdeveloper.project2.domain.models.remote.GetDataFromRemote
import com.gulfappdeveloper.project2.domain.models.remote.ProductDetails
import com.gulfappdeveloper.project2.repositories.RemoteRepository
import kotlinx.coroutines.flow.Flow

class GetProductDetailsUseCase(
    private val remoteRepository: RemoteRepository
) {
    suspend operator fun invoke():Flow<GetDataFromRemote<List<ProductDetails>>>{
        return remoteRepository.getProductDetails()
    }
}