package com.gulfappdeveloper.project2.domain.models.remote.get.license

@kotlinx.serialization.Serializable
data class LicenseRequestBody(
    val licenseKey: String,
    val macId: String,
    val ipAddress: String,
)
