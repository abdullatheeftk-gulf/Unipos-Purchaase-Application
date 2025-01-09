package com.gulfappdeveloper.project2.domain.models.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class LocalPurchaseMaster(
    val additionalAmount: Float,
    val additionalDiscount: Float,
    val discountAmount:Float,
    val freight: Float,

    // differ from the purchase master
    val clientId: Int,
    val clientName:String,

    // differ from the purchase master
    val purDate: Date,
    val refInvNo: String,
    val sequenceNo: Int,
    val taxable: Float,
    val terminal: String,
    val totalAmount: Float,
    val totalTax: Float,
    @PrimaryKey
    val userId: Int,
    val isCashPurchase:Boolean = false
)
