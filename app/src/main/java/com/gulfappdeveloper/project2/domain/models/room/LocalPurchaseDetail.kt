package com.gulfappdeveloper.project2.domain.models.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalPurchaseDetail(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    /*val barcode: String,
    val disc:Float,
    val discAmount: Float,
    val gross: Float,
    val netAmount: Float,
    val proId: Int,
    val proQty: Float,
    val proRate: Float,
    val taxAmount: Float,
    val taxPercentage: Float,
    val taxable: Float,
    val unitId: Int*/
    val productId: Int,
    val productName: String,
    val productRate: Float,
    val unit: String,
    val unitId:Int,
    val barcode: String,
    val vat: Float,
    val qty: Float,
    val disc: Float,
    val net:Float
)
