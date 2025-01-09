package com.gulfappdeveloper.project2.domain.models.remote.get

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val barcode: String,
    val productId: Int,
    val productName: String,
    val purchaseDiscount: Float,
    val rate: Float,
    val unitId: Int,
    val unitName: String,
    val vatPercentage: Float
)