package com.gulfappdeveloper.project2.domain.models.barcode_print

import kotlinx.serialization.Serializable

@Serializable
data class BarcodePrintSubmit(
    val barcodeDesignId: String,
    val barcodePrintDTLs: List<BarcodePrintDTL>,
    val expairyDate: String,
    val manuFactDate: String,
    val startingPosition: String
)