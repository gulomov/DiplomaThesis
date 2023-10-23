package com.deploma.work.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.deploma.work.database.dao.ProductListDao
import com.deploma.work.database.entity.ProductListEntity

@Database(entities = [ProductListEntity::class], version = 1)
abstract class DiplomaThesisDatabase : RoomDatabase() {
    abstract fun ProductListDao(): ProductListDao
}
