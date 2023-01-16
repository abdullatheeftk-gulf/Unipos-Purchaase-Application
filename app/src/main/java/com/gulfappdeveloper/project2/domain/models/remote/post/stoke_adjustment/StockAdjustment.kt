package com.gulfappdeveloper.project2.domain.models.remote.post.stoke_adjustment

import com.gulfappdeveloper.project2.domain.models.remote.get.stock_adjustment.ProductStock

@kotlinx.serialization.Serializable
data class StockAdjustment(
    val stockAdjustments:List<ProductStock>,
    val userId:Int
)
