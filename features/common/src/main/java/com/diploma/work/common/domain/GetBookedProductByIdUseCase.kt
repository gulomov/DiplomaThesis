package com.diploma.work.common.domain

import com.diploma.work.repository.repository.AllProductsRepository
import com.diploma.work.repository.repository.BookedProductsRepository
import javax.inject.Inject

class GetBookedProductByIdUseCase @Inject constructor(
    private val bookedProductsRepository: BookedProductsRepository
) {
    operator fun invoke(productId: Int) = bookedProductsRepository.getBookedProduct(productId)
}