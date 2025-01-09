package com.gulfappdeveloper.project2.domain.models.barcode_print

import kotlinx.serialization.Serializable

@Serializable
data class BarcodePrintDTL(
    val barcode: String,
    val quantity: String
)