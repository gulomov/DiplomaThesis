package com.diploma.work.repository.repository

import com.diploma.work.database.converter.Converters
import com.diploma.work.database.dao.ProductsDao
import com.diploma.work.database.entity.FavoriteProductsEntity
import com.diploma.work.repository.data.FavoriteProduct
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteProductsRepository @Inject constructor(
    private val roomDao: ProductsDao,
) {
    suspend fun saveToFavoriteProduct(favoriteProduct: FavoriteProduct) =
        roomDao.saveToFavoriteProducts(
            favoriteProduct.asEntity()
        )

    suspend fun deleteFromFavoriteProducts(productId: Int) =
        roomDao.deleteFromFavoriteProducts(productId)

    suspend fun isProductSavedToFavorites(productId: Int): Boolean =
        roomDao.getFavouriteProduct(productId).map {
            it?.id == productId
        }.firstOrNull() ?: false
}
