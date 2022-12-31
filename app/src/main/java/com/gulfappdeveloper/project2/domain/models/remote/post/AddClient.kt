package com.gulfappdeveloper.project2.domain.models.remote.post

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class AddClient(
    val accountId: Int=1,
    val accountName: String,
    val buildingNumber: String,
    val cityName: String,
    val citySubdivisionName: String,
    val country: String,
    @SerialName("countrySubentity")
    val countrySubEntity: String,
    val nat: String,
    val phoneOne: String,
    val phoneTwo: String,
    val plotIdentification: String,
    val postalZone: String,
    val streetName: String,
    val taxId: String
)