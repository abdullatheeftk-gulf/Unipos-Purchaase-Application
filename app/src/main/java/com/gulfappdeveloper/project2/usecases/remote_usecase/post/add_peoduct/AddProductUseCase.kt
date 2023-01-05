package com.gulfappdeveloper.project2.usecases.remote_usecase.post.add_peoduct

import com.gulfappdeveloper.project2.domain.models.remote.post.AddProduct
import com.gulfappdeveloper.project2.repositories.RemoteRepository

class AddProductUseCase(
    private val remoteRepository: RemoteRepository
) {
    suspend operator fun invoke(url: String, addProduct: AddProduct) =
        remoteRepository.addProduct(url = url, addProduct = addProduct)
}