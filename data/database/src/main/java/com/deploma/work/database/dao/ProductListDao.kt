package com.deploma.work.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.deploma.work.database.entity.ProductListEntity

@Dao
interface ProductListDao {
    @Query("SELECT * FROM product_list")
    suspend fun getProductList(): List<ProductListEntity>
}
