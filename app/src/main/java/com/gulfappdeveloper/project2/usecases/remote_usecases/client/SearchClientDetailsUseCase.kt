package com.gulfappdeveloper.project2.usecases.remote_usecases.client

import com.gulfappdeveloper.project2.domain.models.remote.get.ClientDetails
import com.gulfappdeveloper.project2.domain.models.remote.get.GetDataFromRemote
import com.gulfappdeveloper.project2.repositories.RemoteRepository
import kotlinx.coroutines.flow.Flow

class SearchClientDetailsUseCase(
    private val repository: RemoteRepository
) {
    suspend operator fun invoke(url: String): Flow<GetDataFromRemote<List<ClientDetails>>> {
       return repository.searchClientDetails(url = url)
    }
}