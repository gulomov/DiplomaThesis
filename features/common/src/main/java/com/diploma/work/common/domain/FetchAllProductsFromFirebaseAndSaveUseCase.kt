package com.diploma.work.common.domain

import com.diploma.work.repository.repository.AllProductsRepository
import com.diploma.work.repository.repository.FavoriteProductsRepository
import javax.inject.Inject

class FetchAllProductsFromFirebaseAndSaveUseCase @Inject constructor(
    private val allProductsRepository: AllProductsRepository
) {
    suspend operator fun invoke() = allProductsRepository.fetchAndSaveAllProductsFromFirebase()
}