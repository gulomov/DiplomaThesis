package com.diploma.work.repository.repository

import com.diploma.work.database.dao.ProductsDao
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val roomDao: ProductsDao
) {
    fun getProductDetails(productId: String) = roomDao.getProductList(productId).map {

    }
}