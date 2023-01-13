package com.gulfappdeveloper.project2.domain.models.remote.post

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class PurchaseMaster(
    @SerialName("additionalAmnt")
    val additionalAmount: Float,
    val additionalDiscount: Float,
    val discountAmount:Float,
    @SerialName("frieght")
    val freight: Float,
    val partyAccId: Int,
    val purDate: String,
    val refInvNo: String,
    val sequenceNo: Int,
    val taxable: Float,
    val terminal: String,
    @SerialName("totalAmnt")
    val totalAmount: Float,
    val totalTax: Float,
    val userId: Int
)