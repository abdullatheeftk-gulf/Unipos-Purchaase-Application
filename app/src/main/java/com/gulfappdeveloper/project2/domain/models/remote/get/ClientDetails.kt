package com.gulfappdeveloper.project2.domain.models.remote.get

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClientDetails(
    @SerialName("id")
    val clientId:Int,
    @SerialName("name")
    val clientName:String,
)
