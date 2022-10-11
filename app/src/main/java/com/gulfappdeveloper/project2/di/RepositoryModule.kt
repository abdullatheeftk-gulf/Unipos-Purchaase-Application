package com.gulfappdeveloper.project2.di

import com.gulfappdeveloper.project2.repositories.RemoteRepository
import com.gulfappdeveloper.project2.usecases.UseCase
import com.gulfappdeveloper.project2.usecases.remote_usecases.GetClientDetailsUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecases.GetProductDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUseCase(remoteRepository: RemoteRepository): UseCase {
        return UseCase(
            getClientDetailsUseCase = GetClientDetailsUseCase(remoteRepository = remoteRepository),
            getProductDetailsUseCase = GetProductDetailsUseCase(remoteRepository = remoteRepository)
        )
    }
}