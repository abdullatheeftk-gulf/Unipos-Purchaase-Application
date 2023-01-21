package com.gulfappdeveloper.project2.usecases.remote_usecase.get.login

import com.gulfappdeveloper.project2.repositories.RemoteRepository

class LoginUseCase(
    private val remoteRepository: RemoteRepository
) {
    suspend operator fun invoke(url:String) =
        remoteRepository.loginUser(url = url)
}