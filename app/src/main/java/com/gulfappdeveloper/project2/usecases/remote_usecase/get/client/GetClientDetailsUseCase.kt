package com.gulfappdeveloper.project2.usecases.remote_usecase.get.client

import com.gulfappdeveloper.project2.domain.models.remote.get.ClientDetails
import com.gulfappdeveloper.project2.domain.models.remote.get.GetDataFromRemote
import com.gulfappdeveloper.project2.repositories.RemoteRepository
import kotlinx.coroutines.flow.Flow

class GetClientDetailsUseCase(
    private val remoteRepository: RemoteRepository
) {

    suspend operator fun invoke(url:String):Flow<GetDataFromRemote<List<ClientDetails>>>{
        return remoteRepository.getClientDetails(url = url)
    }
}