package com.deploma.work.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.deploma.work.database.entity.ProductListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductListDao {
    @Query("SELECT * FROM product_list")
    fun getProductList(): Flow<List<ProductListEntity>>
}
