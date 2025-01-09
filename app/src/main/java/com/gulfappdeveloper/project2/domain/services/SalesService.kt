package com.gulfappdeveloper.project2.domain.services

import com.gulfappdeveloper.project2.domain.models.remote.get.GetDataFromRemote
import com.gulfappdeveloper.project2.domain.models.remote.get.WelcomeMessage
import kotlinx.coroutines.flow.Flow

interface SalesService {

    suspend fun getWelcomeMessage(url: String): Flow<GetDataFromRemote<WelcomeMessage>>
}