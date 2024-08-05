package com.diploma.work.home.recommendations

import com.diploma.work.repository.data.AllProductsItem

data class RecommendationUiState(
    val products: List<AllProductsItem> = emptyList(),
    val favoriteIds: List<Int> = emptyList()
)
