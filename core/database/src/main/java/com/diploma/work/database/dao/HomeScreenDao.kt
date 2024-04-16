package com.diploma.work.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.diploma.work.database.entity.HomeRecommendationsEntity
import com.diploma.work.database.entity.NewsInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeScreenDao {
    @Query("SELECT * FROM news_info")
    fun getNewsInfo(): Flow<List<NewsInfoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNewsInfo(newsInfoEntity: NewsInfoEntity)

    @Query("SELECT * FROM home_recommendations")
    fun getHomeRecommendations(): Flow<List<HomeRecommendationsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveHomeRecommendations(homeRecommendationsEntity: HomeRecommendationsEntity)
}