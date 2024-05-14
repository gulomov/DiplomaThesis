package com.diploma.work.booking.domain

import com.diploma.work.repository.data.BookedProduct
import com.diploma.work.repository.repository.BookedProductsRepository
import javax.inject.Inject

class SaveBookedProductUseCase @Inject constructor(
    private val bookedProductsRepository: BookedProductsRepository
) {
    suspend operator fun invoke(bookedProduct: BookedProduct) =
        bookedProductsRepository.saveBookedProduct(bookedProduct)
}