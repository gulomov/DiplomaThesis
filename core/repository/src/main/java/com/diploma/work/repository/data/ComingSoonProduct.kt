package com.diploma.work.repository.data

import com.diploma.work.database.entity.ComingSoonProductEntity

data class ComingSoonProductList(
    val comingSoonProductList : List<ComingSoonProduct> = emptyList()
)

data class ComingSoonProduct(
    val id: Int? = null,
    val image: String? = null,
    val startingDate: String? = null,
    val brand: String? = null
) {
    fun asEntity() = ComingSoonProductEntity(
        id = id ?: 0,
        image = image.toString(),
        startingDate = startingDate.toString(),
        brand = brand.toString()
    )
}