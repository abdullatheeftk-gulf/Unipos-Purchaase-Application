package com.gulfappdeveloper.project2.domain.models.remote.get

import kotlinx.serialization.Serializable

@Serializable
data class ProductDetails(
    val productId:Int,
    val productName:String,
    val productRate:Float,
    val unit:String,
    val stoke:Float,
    val barcode:String,
    val vat:Float
)
