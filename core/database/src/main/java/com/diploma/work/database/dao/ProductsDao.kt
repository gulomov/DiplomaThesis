package com.diploma.work.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.diploma.work.database.entity.TopProductsListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {
    @Query("SELECT * FROM top_products_list WHERE id = :productId")
    fun getTopProductDetail(productId: String): Flow<TopProductsListEntity>
}
