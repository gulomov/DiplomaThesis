package com.diploma.work.repository.repository

import com.diploma.work.database.converter.Converters
import com.diploma.work.database.dao.ProductsDao
import com.diploma.work.repository.data.TopProductItem
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val roomDao: ProductsDao
) {
    fun getProductDetails(productId: String) = roomDao.getTopProductDetail(productId).map {
        TopProductItem(
            address = it.address,
            images = Converters().toImagesList(it.imageUrl),
            title = it.title,
            salePercentage = it.salePercentage,
            saleStartsDate = it.saleStartsDate,
            saleEndsDate = it.saleEndsDate
        )
    }
}