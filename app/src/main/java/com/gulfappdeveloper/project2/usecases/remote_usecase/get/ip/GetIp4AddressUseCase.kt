package com.gulfappdeveloper.project2.usecases.remote_usecase.get.ip

import com.gulfappdeveloper.project2.repositories.RemoteRepository

class GetIp4AddressUseCase(
    private val remoteRepository: RemoteRepository
) {
    suspend operator fun invoke(url: String) =
        remoteRepository.getIp4Address(url = url)
}