package com.gulfappdeveloper.project2.domain.models.remote.post

import kotlinx.serialization.Serializable

@Serializable
data class AddProduct(
    val barcode: String,
    val isInclusive: Boolean,
    val isScale: Boolean,
    val localName: String,
    val mrp: Float,
    val openingStock: Float,
    val pGroupId: Int,
    val productCode: Int,
    val productId: Int,
    val productName: String,
    val purchaseDis: Float,
    val purchasePrice: Float,
    val saleDis: Float,
    val sellingPrice: Float,
    val specification: String,
    val tCategoryId: Int,
    val unitId: Int,
    val userId: Int
)