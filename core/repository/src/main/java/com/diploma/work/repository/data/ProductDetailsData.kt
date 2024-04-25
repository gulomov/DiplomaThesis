package com.diploma.work.repository.data

import com.diploma.work.database.entity.ProductImages
import com.google.firebase.database.PropertyName

data class ProductDetailsData(
    @PropertyName("id")
    val id: Int? = null,
    @PropertyName("images")
    val images: List<ProductImages>? = null,
    @PropertyName("title")
    val title: String? = null,
    @PropertyName("salePercentage")
    val salePercentage: Int? = null,
    @PropertyName("saleStartsDate")
    val saleStartsDate: String? = null,
    @PropertyName("saleEndsDate")
    val saleEndsDate: String? = null,
    @PropertyName("address")
    val address: String? = null
)
