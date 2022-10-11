package com.gulfappdeveloper.project2.usecases.remote_usecases

import com.gulfappdeveloper.project2.domain.models.ClientDetails
import com.gulfappdeveloper.project2.repositories.RemoteRepository
import kotlinx.coroutines.flow.Flow

class GetClientDetailsUseCase(
    private val remoteRepository: RemoteRepository
) {

    suspend operator fun invoke():Flow<List<ClientDetails>>{
        return remoteRepository.getClientDetails()
    }
}