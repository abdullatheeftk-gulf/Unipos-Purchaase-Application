package com.gulfappdeveloper.project2.usecases.remote_usecases

import com.gulfappdeveloper.project2.domain.models.remote.ClientDetails
import com.gulfappdeveloper.project2.domain.models.remote.GetDataFromRemote
import com.gulfappdeveloper.project2.repositories.RemoteRepository
import kotlinx.coroutines.flow.Flow

class GetClientDetailsUseCase(
    private val remoteRepository: RemoteRepository
) {

    suspend operator fun invoke(url:String):Flow<GetDataFromRemote<List<ClientDetails>>>{
        return remoteRepository.getClientDetails(url = url)
    }
}