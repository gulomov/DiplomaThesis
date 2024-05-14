package com.diploma.work.prdoductdetail.domain

import com.diploma.work.repository.repository.ProductDetailsRepository
import javax.inject.Inject

class IsProductBookedUseCase @Inject constructor(
    private val productsDetailsRepository: ProductDetailsRepository
) {
   suspend operator fun invoke(productId: Int) = productsDetailsRepository.isProductBooked(productId)
}