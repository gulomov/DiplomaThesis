package com.diploma.work.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.diploma.work.database.dao.HomeScreenDao
import com.diploma.work.database.dao.ProductListDao
import com.diploma.work.database.entity.HomeRecommendationsEntity
import com.diploma.work.database.entity.NewsInfoEntity
import com.diploma.work.database.entity.ProductListEntity

@Database(
    entities = [
        ProductListEntity::class,
        NewsInfoEntity::class,
        HomeRecommendationsEntity::class
    ], version = 1
)
abstract class DiplomaThesisDatabase : RoomDatabase() {
    abstract fun ProductListDao(): ProductListDao
    abstract fun HomeScreenDao(): HomeScreenDao
}
