package com.gulfappdeveloper.project2.usecases.remote_usecase.get.add_product

import com.gulfappdeveloper.project2.repositories.RemoteRepository

class SearchProductGroupsUseCase(
    private val remoteRepository: RemoteRepository
) {
    suspend operator fun invoke(url:String) = remoteRepository.searchProductGroups(url = url)
}