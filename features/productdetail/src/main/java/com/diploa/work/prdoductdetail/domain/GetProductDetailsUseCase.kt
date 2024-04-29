package com.diploa.work.prdoductdetail.domain

import com.diploma.work.repository.repository.ProductRepository
import javax.inject.Inject

class GetProductDetailsUseCase
    @Inject
    constructor(
        private val productRepository: ProductRepository,
    ) {
        operator fun invoke(productId: String) = productRepository.getProductDetails(productId)
    }
