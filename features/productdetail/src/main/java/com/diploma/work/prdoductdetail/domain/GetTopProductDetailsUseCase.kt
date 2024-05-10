package com.diploma.work.prdoductdetail.domain

import com.diploma.work.repository.repository.ProductRepository
import javax.inject.Inject

class GetTopProductDetailsUseCase @Inject constructor(
    private val productRepository: ProductRepository,
) {
    operator fun invoke(productId: String) = productRepository.getTopProductDetails(productId)
}
