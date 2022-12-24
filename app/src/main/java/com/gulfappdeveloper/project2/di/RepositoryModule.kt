package com.gulfappdeveloper.project2.di

import com.gulfappdeveloper.project2.repositories.DataStoreRepository
import com.gulfappdeveloper.project2.repositories.RemoteRepository
import com.gulfappdeveloper.project2.usecases.UseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.base_url_use_cases.ReadBaseUrlUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.base_url_use_cases.SaveBaseUrlUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.operation_counter_use_cases.ReadOperationCountUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.operation_counter_use_cases.UpdateOperationCountUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecases.GetClientDetailsUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecases.GetProductDetailsUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecases.GetWelcomeMessageUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecases.client.SearchClientDetailsUseCase
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
    fun provideUseCase(
        remoteRepository: RemoteRepository,
        dataStoreRepository: DataStoreRepository,
    ): UseCase {
        return UseCase(

            getWelcomeMessageUseCase = GetWelcomeMessageUseCase(remoteRepository = remoteRepository),

            getClientDetailsUseCase = GetClientDetailsUseCase(remoteRepository = remoteRepository),
            searchClentDetailsUseCase = SearchClientDetailsUseCase(repository = remoteRepository),

            getProductDetailsUseCase = GetProductDetailsUseCase(remoteRepository = remoteRepository),


            updateOperationCountUseCase = UpdateOperationCountUseCase(dataStoreRepository = dataStoreRepository),
            saveBaseUrlUseCase = SaveBaseUrlUseCase(dataStoreRepository = dataStoreRepository),


            readOperationCountUseCase = ReadOperationCountUseCase(dataStoreRepository = dataStoreRepository),
            readBaseUrlUseCase = ReadBaseUrlUseCase(dataStoreRepository = dataStoreRepository)

        )
    }
}