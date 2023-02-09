package com.gulfappdeveloper.project2.domain.models.remote.get.price_adjustment

@kotlinx.serialization.Serializable
data class ProductForPriceAdjustment(
    val barcode: String,
    val mrp: Float,
    val productId: Int,
    val productName: String,
    val purchasePrice: Float,
    val salePrice: Float,
    val stock: Float
)