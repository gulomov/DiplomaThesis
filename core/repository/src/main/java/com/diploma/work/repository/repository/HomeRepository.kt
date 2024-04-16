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
                    newsList.forEach {
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

    fun getNewsInfo(): Flow<List<NewsItem>> = flow {
        println("Success in news repository entered")
        roomDao.getNewsInfo().collect { newsEntityList ->
            emit(newsEntityList.map { entity ->
                println("Success in news repository entered: $newsEntityList")
                NewsItem(
                    image = entity.image,
                    body = entity.body,
                    title = entity.title
                )
            })
        }
    }


    fun fetchAndSaveHomeRecommendationsFromFirebase(): Flow<List<RecommendationItem>> =
        flow {
            try {
                fetchFromDatabase<RecommendationsList>("home/recommendations", firebaseDatabase)
                    .collect { data ->
                        withContext(Dispatchers.IO) {
                            data?.recommendationsList?.map {
                                println("Success in recommendations repository: $it")
                                roomDao.saveHomeRecommendations(
                                    HomeRecommendationsEntity(
                                        id = it.id ?: 0,
                                        imageUrl = it.image.orEmpty()
                                    )
                                )
                            }
                            roomDao.getHomeRecommendations().map { data ->
                                data.map {
                                    RecommendationItem(id = it.id, image = it.imageUrl)
                                }
                            }
                        }
                    }
            } catch (e: Exception) {
                println("Error in Repository $e")
                emit(emptyList())
            }
        }

    fun getRecommendations() = roomDao.getHomeRecommendations().map { recommendations ->
        recommendations.map {
            RecommendationItem(id = it.id, image = it.imageUrl)
        }
    }
}