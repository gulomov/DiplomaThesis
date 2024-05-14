package com.diploma.work.repository.repository

import com.diploma.work.database.dao.ProductsDao
import com.diploma.work.repository.data.BookedProduct
import javax.inject.Inject

class BookedProductsRepository @Inject constructor(
    private val roomDao: ProductsDao
) {
    suspend fun saveBookedProduct(bookedProduct: BookedProduct) = roomDao.saveToBookedProducts(
        bookedProduct = bookedProduct.asEntity()
    )

    fun getBookedProduct(productId: Int) = roomDao.getBookedProductById(productId)
}