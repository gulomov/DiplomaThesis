package com.diploma.work.repository.repository

import com.diploma.work.database.dao.ProductsDao
import com.diploma.work.repository.data.FavoriteProduct
import javax.inject.Inject

class FavoriteProductsRepository @Inject constructor(
    private val roomDao: ProductsDao,
) {
    fun saveFavoriteProduct(favoriteProduct: FavoriteProduct) =
        roomDao.saveFavoriteProducts(favoriteProductsEntity = favoriteProduct.asEntity())
}