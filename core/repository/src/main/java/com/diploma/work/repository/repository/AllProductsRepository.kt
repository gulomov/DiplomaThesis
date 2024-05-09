package com.diploma.work.repository.repository

import com.diploma.work.database.converter.Converters
import com.diploma.work.database.dao.ProductsDao
import com.diploma.work.database.entity.AllProductsListEntity
import com.diploma.work.database.entity.BrandsListEntity
import com.diploma.work.repository.data.AllProductsItem
import com.diploma.work.repository.data.AllProductsList
import com.diploma.work.repository.data.BrandsItem
import com.diploma.work.repository.data.BrandsList
import com.diploma.work.repository.generic.fetchFromDatabase
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
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
                data?.allProductsList?.map {
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
                brand = it.brand
            )
        }
    }

    suspend fun getProductsByBrandName(brandName: String) =
        roomDao.getAllProductsFlow().map { allProducts ->
            allProducts.filter { it.brand == brandName }
        }.first().map {
            AllProductsItem(
                id = it.id,
                images = Converters().toImagesList(it.imageUrl),
                title = it.title,
                salePercentage = it.salePercentage,
                saleStartsDate = it.saleStartsDate,
                saleEndsDate = it.saleEndsDate,
                priceOnSale = it.priceOnSale,
                originalPrice = it.originalPrice,
                brand = it.brand
            )
        }

    suspend fun fetchAndSaveBrandsFromFirebase() {
        fetchFromDatabase<BrandsList>(
            "home/brands",
            firebaseDatabase
        ).collect { data ->
            withContext(Dispatchers.IO) {
                data?.brandsList?.map {
                    roomDao.saveToBrandsEntity(
                        BrandsListEntity(
                            brandId = it.brandId ?: 0,
                            brand = it.brand.toString(),
                            imageUrl = it.imageUrl.toString()
                        )
                    )
                }
            }
        }
    }

    fun getBrands() = roomDao.getAllBrandsFlow().map { allBrands ->
        allBrands.map {
            BrandsItem(
                brandId = it.brandId, imageUrl = it.imageUrl, brand = it.brand
            )
        }
    }
}