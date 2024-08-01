package com.diploma.work.repository.data

import com.diploma.work.database.entity.ProductImages
import com.diploma.work.database.entity.ProductSizes

data class AllProductsList(
    val allProductsList: List<AllProductsItem>? = emptyList(),
)

data class AllProductsItem(
    val address: String? = null,
    val id: Int? = null,
    val images: List<ProductImages>? = null,
    val title: String? = null,
    val salePercentage: Int? = null,
    val saleStartsDate: String? = null,
    val saleEndsDate: String? = null,
    val originalPrice: Int? = null,
    val priceOnSale: Int? = null,
    val sizes: List<ProductSizes>? = null,
    val brand: String? = null
) {
    fun asFavoriteProduct() = FavoriteProduct(
        address = address,
        id = id,
        images = images,
        title = title,
        salePercentage = salePercentage,
        saleStartsDate = saleStartsDate,
        saleEndsDate = saleEndsDate,
        originalPrice = originalPrice,
        priceOnSale = priceOnSale,
        sizes = sizes
    )
}