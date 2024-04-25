package com.diploma.work.repository.data

import com.diploma.work.database.entity.ProductImages
import com.google.firebase.database.PropertyName

data class ProductDetailsData(
    val id: Int? = null,
    val images: List<ProductImages>? = null,
    val title: String? = null,
    val salePercentage: Int? = null,
    val saleStartsDate: String? = null,
    val saleEndsDate: String? = null,
    val address: String? = null,
    val originalPrice: Int? = null,
    val priceOnSale: Int? = null
)
