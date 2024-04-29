package com.diploma.work.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.diploma.work.database.converter.Converters
import com.diploma.work.database.dao.HomeScreenDao
import com.diploma.work.database.dao.ProductsDao
import com.diploma.work.database.entity.HomeRecommendationsEntity
import com.diploma.work.database.entity.NewsInfoEntity
import com.diploma.work.database.entity.ProductListEntity
import com.diploma.work.database.entity.TopProductsListEntity

@Database(
    entities = [
        ProductListEntity::class,
        NewsInfoEntity::class,
        HomeRecommendationsEntity::class,
        TopProductsListEntity::class,
    ],
    version = 1,
)
@TypeConverters(Converters::class)
abstract class DiplomaThesisDatabase : RoomDatabase() {
    abstract fun ProductDao(): ProductsDao

    abstract fun HomeScreenDao(): HomeScreenDao
}
