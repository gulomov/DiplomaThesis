package com.diploma.work.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.diploma.work.database.entity.ComingSoonProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ComingSoonDao {

    @Insert
    suspend fun insert(product: ComingSoonProductEntity)

    @Query("SELECT * FROM coming_soon_products")
    fun getFavouriteProducts(): Flow<List<ComingSoonProductEntity>>
}