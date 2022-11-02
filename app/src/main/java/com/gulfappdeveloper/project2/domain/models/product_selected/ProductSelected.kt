package com.gulfappdeveloper.project2.domain.models.product_selected

data class ProductSelected(
    val productId: Int,
    val productName: String,
    val productRate: Float,
    val unit: String,
    val barcode: String?,
    val vat: Float,
    val qty: Float,
    val disc: Float = 0f,
    val net:Float
)
