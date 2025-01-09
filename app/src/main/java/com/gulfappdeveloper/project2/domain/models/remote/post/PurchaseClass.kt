package com.gulfappdeveloper.project2.domain.models.remote.post

@kotlinx.serialization.Serializable
data class PurchaseClass(
    val purchaseMaster:PurchaseMaster,
    val purchaseDetails:List<PurchaseDetail>
)