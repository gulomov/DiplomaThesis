package com.diploma.work.repository.repository

import com.diploma.work.database.converter.Converters
import com.diploma.work.database.dao.ProductsDao
import com.diploma.work.database.entity.AllProductsListEntity
import com.diploma.work.repository.data.AllProductsItem
import com.diploma.work.repository.data.AllProductsList
import com.diploma.work.repository.generic.fetchFromDatabase
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AllProductsRepository @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val roomDao: ProductsDao
) {
    suspend fun fetchAndSaveAllProductsFromFirebase() {
        fetchFromDatabase<AllProductsList>(
            "home/allProducts",
            firebaseDatabase
        ).collect { data ->
            withContext(Dispatchers.IO) {
                val existingEntries = roomDao.getAllProductsList().associateBy { it.id }
                data?.allProductsList?.map {
                    val existing = existingEntries[it.id]
                    if (existing == null || existing.id != it.id) {
                        roomDao.saveToAllProductsEntity(
                            AllProductsListEntity(
                                address = it.address.orEmpty(),
                                id = it.id ?: 0,
                                imageUrl = Converters().fromImagesList(it.images.orEmpty()),
                                title = it.title.orEmpty(),
                                salePercentage = it.salePercentage ?: 0,
                                saleStartsDate = it.saleStartsDate.orEmpty(),
                                saleEndsDate = it.saleEndsDate.orEmpty(),
                                originalPrice = it.originalPrice ?: 0,
                                priceOnSale = it.priceOnSale ?: 0,
                                sizes = Converters().fromSizesList(it.sizes.orEmpty()),
                                brand = it.brand.toString()
                            )
                        )
                    }
                }
            }
        }
    }

    fun getAllProducts() = roomDao.getAllProductsFlow().map { allProducts ->
        allProducts.map {
            AllProductsItem(
                id = it.id,
                images = Converters().toImagesList(it.imageUrl),
                title = it.title,
                salePercentage = it.salePercentage,
                saleStartsDate = it.saleStartsDate,
                saleEndsDate = it.saleEndsDate,
                priceOnSale = it.priceOnSale,
                originalPrice = it.originalPrice,
            )
        }
    }
}