package com.gulfappdeveloper.project2.domain.models.remote.post

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class PurchaseDetail(
    val barcode: String,
    val disc:Float,
    @SerialName("discAmnt")
    val discAmount: Float,
    val gross: Float,
    @SerialName("netAmnt")
    val netAmount: Float,
    val proId: Int,
    val proQty: Float,
    val proRate: Float,
    val taxAmount: Float,
    val taxPercentage: Float,
    val taxable: Float,
    val unitId: Int
)