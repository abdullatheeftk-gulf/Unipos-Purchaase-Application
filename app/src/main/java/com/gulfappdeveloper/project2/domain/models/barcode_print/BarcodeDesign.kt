package com.gulfappdeveloper.project2.domain.models.barcode_print

import kotlinx.serialization.Serializable

@Serializable
data class BarcodeDesign(
    val designId: Int,
    val designName: String
)