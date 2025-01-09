package com.gulfappdeveloper.project2.domain.models.remote.get.stock_adjustment

@kotlinx.serialization.Serializable
data class ProductStock(
    val barcode: String,
    val curActualStock: Float,
    val currSystemStock: Float,
    val productId: Int,
    val productName: String,
    val unitId: Int,
    val unitName: String
)