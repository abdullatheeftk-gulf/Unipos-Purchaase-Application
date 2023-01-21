package com.gulfappdeveloper.project2.domain.datastore

@kotlinx.serialization.Serializable
data class UniLicenseDetails(
    val licenseKey: String,
    val licenseType: String,
    val expiryDate: String
)
