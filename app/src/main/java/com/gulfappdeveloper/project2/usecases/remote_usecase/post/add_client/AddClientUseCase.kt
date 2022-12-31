package com.gulfappdeveloper.project2.usecases.remote_usecase.post.add_client

import com.gulfappdeveloper.project2.domain.models.remote.get.GetDataFromRemote
import com.gulfappdeveloper.project2.domain.models.remote.post.AddClient
import com.gulfappdeveloper.project2.repositories.RemoteRepository
import kotlinx.coroutines.flow.Flow

class AddClientUseCase(
    private val remoteRepository: RemoteRepository
) {
    suspend operator fun invoke(url: String,addClient: AddClient):Flow<GetDataFromRemote<AddClient>>{
        return remoteRepository.addClientDetails(url = url, addClient = addClient)
    }
}