package com.diploma.work.repository.data

import com.google.firebase.database.PropertyName

data class BrandsList(
    @PropertyName("brandsList")
    val brandsList: List<BrandsItem> = emptyList()
)

data class BrandsItem(
    @PropertyName("brandId") val brandId: Int? = null,
    @PropertyName("imageUrl") val imageUrl: String? = null,
    @PropertyName("brand") val brand: String? = null
)
