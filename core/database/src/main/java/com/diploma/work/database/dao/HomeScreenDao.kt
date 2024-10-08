package com.diploma.work.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.diploma.work.database.entity.HomeRecommendationsEntity
import com.diploma.work.database.entity.NewsInfoEntity
import com.diploma.work.database.entity.TopProductsListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeScreenDao {
    @Query("SELECT * FROM news_info")
    fun getNewsInfoFlow(): Flow<List<NewsInfoEntity>>

    @Query("SELECT * FROM news_info")
    fun getNewsInfoList(): List<NewsInfoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNewsInfo(newsInfoEntity: NewsInfoEntity)

    @Query("SELECT * FROM home_recommendations")
    fun getHomeRecommendationsFlow(): Flow<List<HomeRecommendationsEntity>>

    @Query("SELECT * FROM home_recommendations")
    fun getHomeRecommendationsList(): List<HomeRecommendationsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveHomeRecommendations(homeRecommendationsEntity: HomeRecommendationsEntity)

    @Query("SELECT * FROM top_products_list")
    fun getTopProductsList(): List<TopProductsListEntity>

    @Query("SELECT * FROM top_products_list")
    fun getTopProductsFlow(): Flow<List<TopProductsListEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTopProductsList(topProductsListEntity: TopProductsListEntity)

    @Query("SELECT * FROM news_info WHERE id = :id ")
    fun getNewsDetail(id: Int) : Flow<NewsInfoEntity>
}
