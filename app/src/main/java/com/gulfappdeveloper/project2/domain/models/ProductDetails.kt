package com.gulfappdeveloper.project2.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class ProductDetails(
    val productId:Int,
    val productName:String,
    val productRate:Float,
    val unit:String,
    val stoke:Float,
    val vat:Float
)
