package com.gulfappdeveloper.project2.domain.models.remote.get

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val productId:Int,
    val productName:String,
    val rate:Float,
    val unitName:String,
    val barcode:String,
    val vatPercentage:Float,
    val purchaseDiscount:Float = 0f
)
