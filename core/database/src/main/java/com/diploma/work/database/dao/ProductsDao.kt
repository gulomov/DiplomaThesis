package com.diploma.work.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.diploma.work.database.entity.FavoriteProductsEntity
import com.diploma.work.database.entity.TopProductsListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {
    @Query("SELECT * FROM top_products_list WHERE id = :productId")
    fun getTopProductDetail(productId: String): Flow<TopProductsListEntity>

    @Query("SELECT * FROM favorite_products")
    fun getFavouriteProducts(): Flow<List<FavoriteProductsEntity>>

    @Query("SELECT * FROM favorite_products WHERE id = :productId")
    fun getFavouriteProduct(productId: Int): Flow<FavoriteProductsEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveToFavoriteProducts(favoriteProductsEntity: FavoriteProductsEntity)

    @Query("DELETE FROM favorite_products WHERE id = :id")
    suspend fun deleteFromFavoriteProducts(id: Int)
}
