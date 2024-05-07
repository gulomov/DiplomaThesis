package com.diploma.work.gallery.domain

import com.diploma.work.repository.repository.AllProductsRepository
import javax.inject.Inject

class GetProductsByBrandNameUseCase @Inject constructor(
    private val allProductsRepository: AllProductsRepository
) {
    suspend operator fun invoke(brandName: String) = allProductsRepository.getProductsByBrandName(brandName)
}