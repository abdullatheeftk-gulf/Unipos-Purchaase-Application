package com.gulfappdeveloper.project2.domain.models.remote.post.add_product

@kotlinx.serialization.Serializable
data class ProductUnit(
    val barcode: String,
    val isInclusive: Boolean,
    val openingStock: Float,
    val proUnitId: Int,
    val productId: Int,
    val productUnitName: String,
    val salesPrice: Float,
    val unitId: Int,
    val unitQty: Float,
    val unitType: String
)