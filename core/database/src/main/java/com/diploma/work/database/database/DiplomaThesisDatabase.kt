package com.diploma.work.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.diploma.work.database.dao.ProductListDao
import com.diploma.work.database.entity.ProductListEntity

@Database(entities = [ProductListEntity::class], version = 1)
abstract class DiplomaThesisDatabase : RoomDatabase() {
    abstract fun ProductListDao(): ProductListDao
}
