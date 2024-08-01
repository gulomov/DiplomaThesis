package com.diploma.work.favorites

import com.diploma.work.repository.data.FavoriteProduct

data class FavoriteScreenState(
    val favoriteProducts: List<FavoriteProduct> = emptyList(),
    val loadingValue: Boolean = true,
)
