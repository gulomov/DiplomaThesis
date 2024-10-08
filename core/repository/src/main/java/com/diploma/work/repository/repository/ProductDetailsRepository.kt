package com.diploma.work.repository.repository

import com.diploma.work.database.converter.Converters
import com.diploma.work.database.dao.ProductsDao
import com.diploma.work.repository.data.ProductDetailsData
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductDetailsRepository @Inject constructor(
    private val roomDao: ProductsDao,
) {
    fun getTopProductDetails(productId: Int) =
        roomDao.getTopProductDetail(productId).map {
            ProductDetailsData(
                id = it.id,
                address = it.address,
                images = Converters().toImagesList(it.imageUrl),
                title = it.title,
                salePercentage = it.salePercentage,
                saleStartsDate = it.saleStartsDate,
                saleEndsDate = it.saleEndsDate,
                priceOnSale = it.priceOnSale,
                originalPrice = it.originalPrice,
                sizes = Converters().toSizesList(it.sizes),
            )
        }

    fun getProductDetails(productId: Int) = roomDao.getProductDetail(productId).map {
        ProductDetailsData(
            id = it.id,
            address = it.address,
            images = Converters().toImagesList(it.imageUrl),
            title = it.title,
            salePercentage = it.salePercentage,
            saleStartsDate = it.saleStartsDate,
            saleEndsDate = it.saleEndsDate,
            priceOnSale = it.priceOnSale,
            originalPrice = it.originalPrice,
            sizes = Converters().toSizesList(it.sizes),
        )
    }

    suspend fun isProductBooked(productId: Int): Boolean? {
        return roomDao.getBookedProducts()
            .map { products -> products.any { it.productId == productId } }
            .firstOrNull()
    }
}

