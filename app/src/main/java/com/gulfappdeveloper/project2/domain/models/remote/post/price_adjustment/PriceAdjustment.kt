package com.gulfappdeveloper.project2.domain.models.remote.post.price_adjustment

@kotlinx.serialization.Serializable
data class PriceAdjustment(
    val mrp: Float,
    val barcode: String,
    val productId: Int,
    val purchasePrice: Float,
    val salePrice: Float
)