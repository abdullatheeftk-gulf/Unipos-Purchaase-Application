package com.gulfappdeveloper.project2.domain.models.remote.get.license

@kotlinx.serialization.Serializable
data class LicenseMessage(
    val message: String,
    val duration: Int,
    val licenseType: String,
    val status: Int,
    val startDate: String,
    val expiryDate: String?,
)
