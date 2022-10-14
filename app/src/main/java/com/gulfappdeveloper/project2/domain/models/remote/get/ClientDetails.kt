package com.gulfappdeveloper.project2.domain.models.remote.get

import kotlinx.serialization.Serializable

@Serializable
data class ClientDetails(
    val clientId:Int,
    val clientName:String,
    val taxId:String
)
