package com.deploma.work.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.deploma.work.data.dao.ProductListDao
import com.deploma.work.data.entity.ProductListEntity

@Database(entities = [ProductListEntity::class], version = 1)
abstract class DiplomaThesisDatabase : RoomDatabase() {
    abstract fun ProductListDao(): ProductListDao
}
