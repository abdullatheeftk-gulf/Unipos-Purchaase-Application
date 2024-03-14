package com.gulfappdeveloper.project2.presentation.ui_util

import com.gulfappdeveloper.project2.domain.models.remote.Error
import com.gulfappdeveloper.project2.domain.models.remote.get.Product

data class ProductBarcodeSearchTransfer(
    val product: Product? = null,
    val error:Error? = null,
)
