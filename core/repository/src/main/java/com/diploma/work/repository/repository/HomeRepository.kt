package com.diploma.work.repository.repository

import com.diploma.work.database.dao.HomeScreenDao
import com.diploma.work.database.entity.HomeRecommendationsEntity
import com.diploma.work.database.entity.NewsInfoEntity
import com.diploma.work.repository.data.NewsInfo
import com.diploma.work.repository.data.NewsItem
import com.diploma.work.repository.data.RecommendationItem
import com.diploma.work.repository.data.RecommendationsList
import com.diploma.work.repository.generic.fetchFromDatabase
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val roomDao: HomeScreenDao
) {

    suspend fun fetchAndSaveNewsInfoFromFirebase() {
        fetchFromDatabase<NewsInfo>("home/news", firebaseDatabase).collect { newsInfo ->
            newsInfo?.newsList?.let { newsList ->
                withContext(Dispatchers.IO) {
                    val existingEntries = roomDao.getNewsInfoList().associateBy { it.image }
                    newsList.forEach {
                        val existing = existingEntries[it.image]
                        if (existing == null || existing.image != it.image) {
                            roomDao.saveNewsInfo(
                                NewsInfoEntity(
                                    image = it.image,
                                    body = it.body,
                                    title = it.title
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    fun getNewsInfo(): Flow<List<NewsItem>> = flow {
        roomDao.getNewsInfoFlow().collect { newsEntityList ->
            emit(newsEntityList.map { entity ->
                NewsItem(
                    image = entity.image,
                    body = entity.body,
                    title = entity.title
                )
            })
        }
    }


    suspend fun fetchAndSaveHomeRecommendationsFromFirebase() {
        fetchFromDatabase<RecommendationsList>(
            "home/recommendations",
            firebaseDatabase
        ).collect { data ->
            withContext(Dispatchers.IO) {
                val existingEntries = roomDao.getHomeRecommendationsList().associateBy { it.id }
                data?.recommendationsList?.map {
                    val existing = existingEntries[it.id]
                    if (existing == null || existing.id != it.id) {
                        roomDao.saveHomeRecommendations(
                            HomeRecommendationsEntity(
                                id = it.id ?: 0,
                                imageUrl = it.image.orEmpty()
                            )
                        )
                    }
                }
            }
        }
    }

    fun getRecommendations() = roomDao.getHomeRecommendationsFlow().map { recommendations ->
        recommendations.map {
            RecommendationItem(id = it.id, image = it.imageUrl)
        }
    }
}