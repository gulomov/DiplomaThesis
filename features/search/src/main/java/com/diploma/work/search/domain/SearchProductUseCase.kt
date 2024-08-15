package com.diploma.work.search.domain

import com.diploma.work.repository.repository.AllProductsRepository
import javax.inject.Inject

class SearchProductUseCase @Inject constructor(
    private val allProductsRepository: AllProductsRepository
) {
    operator fun invoke(searchQuery: String) = allProductsRepository.searchProductsFlow(searchQuery)
}