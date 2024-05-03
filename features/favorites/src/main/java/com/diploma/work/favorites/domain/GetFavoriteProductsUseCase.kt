package com.diploma.work.favorites.domain

import com.diploma.work.repository.repository.FavoriteProductsRepository
import javax.inject.Inject

class GetFavoriteProductsUseCase @Inject constructor(
    private val favoriteProductRepository: FavoriteProductsRepository
) {
    operator fun invoke() = favoriteProductRepository.getFavorites()
}