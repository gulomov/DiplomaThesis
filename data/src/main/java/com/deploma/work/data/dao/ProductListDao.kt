package com.deploma.work.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.deploma.work.data.entity.ProductListEntity

@Dao
interface ProductListDao {
    @Query("SELECT * FROM product_list")
    suspend fun getProductList(): List<ProductListEntity>
}
