package com.diploma.work.repository.data

import com.diploma.work.database.entity.BookedProductEntity

data class BookedProduct(
    val productId: Int? = null,
    val bookedDate: Long? = null
) {
    fun asEntity() = BookedProductEntity(productId ?: 0, bookedDate ?: 0)
}
