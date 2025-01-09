package com.gulfappdeveloper.project2.usecases.remote_usecase.get.add_product

import com.gulfappdeveloper.project2.repositories.RemoteRepository

class GetProductGroupsUseCase(
    private val remoteRepository: RemoteRepository
) {
    suspend operator fun invoke(url: String) = remoteRepository.getProductGroups(url = url)
}