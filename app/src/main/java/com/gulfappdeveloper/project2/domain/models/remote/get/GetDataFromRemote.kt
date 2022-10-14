package com.gulfappdeveloper.project2.domain.models.remote.get

import com.gulfappdeveloper.project2.domain.models.remote.Error


sealed class GetDataFromRemote<out T> {
    data class Success<T>(val data:T): GetDataFromRemote<T>()
    data class Failed(val error: Error): GetDataFromRemote<Nothing>()
}


