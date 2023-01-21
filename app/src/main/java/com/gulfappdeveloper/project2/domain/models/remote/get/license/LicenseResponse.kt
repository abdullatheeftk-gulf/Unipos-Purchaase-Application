package com.gulfappdeveloper.project2.domain.models.remote.get.license


@kotlinx.serialization.Serializable
data class LicenseResponse(
    val message: LicenseMessage,
    val status: Int
)
