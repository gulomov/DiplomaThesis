package com.diploma.work.common.domain

import com.diploma.work.repository.repository.FavoriteProductsRepository
import javax.inject.Inject

class GetFavoriteProductsIdsUseCase @Inject constructor(
    private val favoriteProductsRepository: FavoriteProductsRepository
) {
    operator fun invoke() = favoriteProductsRepository.getFavoriteProductsIds()
}