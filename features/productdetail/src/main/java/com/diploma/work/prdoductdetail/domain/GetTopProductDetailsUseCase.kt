package com.diploma.work.prdoductdetail.domain

import com.diploma.work.repository.repository.ProductDetailsRepository
import javax.inject.Inject

class GetTopProductDetailsUseCase @Inject constructor(
    private val productDetailsRepository: ProductDetailsRepository,
) {
    operator fun invoke(productId: Int) = productDetailsRepository.getTopProductDetails(productId)
}
