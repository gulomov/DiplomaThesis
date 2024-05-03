package com.diploma.work.common.domain

import com.diploma.work.repository.data.FavoriteProduct
import com.diploma.work.repository.repository.FavoriteProductsRepository
import javax.inject.Inject

class SaveToFavoriteProductUseCase @Inject constructor(
    private val productsRepository: FavoriteProductsRepository
) {
    suspend operator fun invoke(favoriteProduct: FavoriteProduct) =
        productsRepository.saveToFavoriteProduct(favoriteProduct)
}