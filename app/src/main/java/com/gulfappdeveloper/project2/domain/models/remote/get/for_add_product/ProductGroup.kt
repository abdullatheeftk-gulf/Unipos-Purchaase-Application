package com.gulfappdeveloper.project2.domain.models.remote.get.for_add_product

@kotlinx.serialization.Serializable
data class ProductGroup(
    val pGroupId: Int,
    val pGroupName: String
)