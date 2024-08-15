package com.diploma.work.search

import com.diploma.work.repository.data.AllProductsItem

data class SearchUiState(
    val products: List<AllProductsItem> = emptyList(),
    val loadingValue: Boolean = true
)
