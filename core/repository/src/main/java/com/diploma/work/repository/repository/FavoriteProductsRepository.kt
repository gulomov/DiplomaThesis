package com.diploma.work.repository.repository

import com.diploma.work.database.converter.Converters
import com.diploma.work.database.dao.FavoritesDao
import com.diploma.work.database.dao.ProductsDao
import com.diploma.work.database.entity.FavoriteProductsEntity
import com.diploma.work.repository.data.FavoriteProduct
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteProductsRepository @Inject constructor(
    private val favoritesDao: FavoritesDao,
) {
    suspend fun saveToFavoriteProduct(favoriteProduct: FavoriteProduct) =
        favoritesDao.saveToFavoriteProducts(favoriteProduct.asEntity())

    suspend fun deleteFromFavoriteProducts(productId: Int) =
        favoritesDao.deleteFromFavoriteProducts(productId)

    suspend fun isProductSavedToFavorites(productId: Int): Boolean =
        favoritesDao.getFavouriteProduct(productId).map {
            it?.id == productId
        }.firstOrNull() ?: false

    fun getFavoriteProductsIds() = favoritesDao.getFavoriteProductIds()

    fun getFavorites() = favoritesDao.getFavouriteProducts().map {
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
