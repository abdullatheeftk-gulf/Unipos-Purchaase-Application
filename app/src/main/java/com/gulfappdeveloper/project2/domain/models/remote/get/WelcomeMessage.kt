package com.gulfappdeveloper.project2.domain.models.remote.get

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WelcomeMessage(
    @SerialName("oemName")
    val message: String
)
