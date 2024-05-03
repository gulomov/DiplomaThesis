package com.diploma.work.common.domain

import com.diploma.work.repository.repository.FavoriteProductsRepository
import javax.inject.Inject

class DeleteFromFavoriteProductsUseCase @Inject constructor(
    private val favoriteProductRepository: FavoriteProductsRepository
) {
    suspend operator fun invoke(productId: Int) =
        favoriteProductRepository.deleteFromFavoriteProducts(productId)
}