package com.diploma.work.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.diploma.work.database.converter.Converters
import com.diploma.work.database.dao.ComingSoonDao
import com.diploma.work.database.dao.FavoritesDao
import com.diploma.work.database.dao.HomeScreenDao
import com.diploma.work.database.dao.ProductsDao
import com.diploma.work.database.entity.AllProductsListEntity
import com.diploma.work.database.entity.BookedProductEntity
import com.diploma.work.database.entity.BrandsListEntity
import com.diploma.work.database.entity.ComingSoonProductEntity
import com.diploma.work.database.entity.FavoriteProductsEntity
import com.diploma.work.database.entity.HomeRecommendationsEntity
import com.diploma.work.database.entity.NewsInfoEntity
import com.diploma.work.database.entity.ProductListEntity
import com.diploma.work.database.entity.TopProductsListEntity
import com.google.common.eventbus.AllowConcurrentEvents

@Database(
    entities = [
        ProductListEntity::class,
        NewsInfoEntity::class,
        HomeRecommendationsEntity::class,
        TopProductsListEntity::class,
        FavoriteProductsEntity::class,
        AllProductsListEntity::class,
        BrandsListEntity::class,
        BookedProductEntity::class,
        ComingSoonProductEntity::class
    ],
    version = 1,
)
@TypeConverters(Converters::class)
abstract class DiplomaThesisDatabase : RoomDatabase() {
    abstract fun HomeScreenDao(): HomeScreenDao
    abstract fun ProductDao(): ProductsDao
    abstract fun FavoritesDao(): FavoritesDao
    abstract fun ComingSoonDao(): ComingSoonDao
}
