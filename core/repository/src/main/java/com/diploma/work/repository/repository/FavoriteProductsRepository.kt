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
        roomDao.saveToFavoriteProducts(favoriteProduct.asEntity())

    suspend fun deleteFromFavoriteProducts(productId: Int) =
        roomDao.deleteFromFavoriteProducts(productId)

    suspend fun isProductSavedToFavorites(productId: Int): Boolean =
        roomDao.getFavouriteProduct(productId).map {
            it?.id == productId
        }.firstOrNull() ?: false

    fun getFavoriteProductsIds() = roomDao.getFavoriteProductIds()

    fun getFavorites() = roomDao.getFavouriteProducts().map {
        it.map { data ->
            FavoriteProduct(
                address = data.address,
                id = data.id,
                images = Converters().toImagesList(data.imageUrl),
                title = data.title,
                salePercentage = data.salePercentage,
                saleStartsDate = data.saleStartsDate,
                saleEndsDate = data.saleEndsDate,
                originalPrice = data.originalPrice,
                priceOnSale = data.priceOnSale,
                sizes = Converters().toSizesList(data.sizes)
            )
        }
    }
}
