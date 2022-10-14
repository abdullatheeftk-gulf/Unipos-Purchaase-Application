package com.gulfappdeveloper.project2.usecases.remote_usecases

import com.gulfappdeveloper.project2.domain.models.remote.get.GetDataFromRemote
import com.gulfappdeveloper.project2.domain.models.remote.get.WelcomeMessage
import com.gulfappdeveloper.project2.repositories.RemoteRepository
import kotlinx.coroutines.flow.Flow

class GetWelcomeMessageUseCase(
    private val remoteRepository: RemoteRepository
) {
    suspend operator fun invoke(url: String): Flow<GetDataFromRemote<WelcomeMessage>> {
        return remoteRepository.getWelcomeMessage(url = url)
    }
}