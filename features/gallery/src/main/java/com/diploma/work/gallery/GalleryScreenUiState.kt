package com.diploma.work.gallery

import com.diploma.work.repository.data.AllProductsItem
import com.diploma.work.repository.data.BrandsItem

data class GalleryScreenUiState(
    val brands: List<BrandsItem> = emptyList(),
    val products: List<AllProductsItem> = emptyList(),
    val favoriteIds: List<Int> = emptyList(),
    val loadingValue: Boolean = true
)
