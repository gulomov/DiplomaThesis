package com.diploma.work.repository.repository

import com.diploma.work.database.converter.Converters
import com.diploma.work.database.dao.HomeScreenDao
import com.diploma.work.database.entity.HomeRecommendationsEntity
import com.diploma.work.database.entity.NewsInfoEntity
import com.diploma.work.database.entity.TopProductsListEntity
import com.diploma.work.repository.data.NewsInfo
import com.diploma.work.repository.data.NewsItem
import com.diploma.work.repository.data.RecommendationItem
import com.diploma.work.repository.data.RecommendationsList
import com.diploma.work.repository.data.TopProductItem
import com.diploma.work.repository.data.TopProductsList
import com.diploma.work.repository.generic.fetchFromDatabase
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val AMOUNT_OF_TOP_PRODUCTS_IN_HOME_SCREEN = 5

class HomeRepository @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val roomDao: HomeScreenDao,
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
                                    title = it.title,
                                ),
                            )
                        }
                    }
                }
            }
        }
    }

    suspend fun fetchAndSaveHomeRecommendationsFromFirebase() {
        fetchFromDatabase<RecommendationsList>(
            "home/recommendations",
            firebaseDatabase,
        ).collect { data ->
            withContext(Dispatchers.IO) {
                val existingEntries = roomDao.getHomeRecommendationsList().associateBy { it.id }
                data?.recommendationsList?.map {
                    val existing = existingEntries[it.id]
                    if (existing == null || existing.id != it.id) {
                        roomDao.saveHomeRecommendations(
                            HomeRecommendationsEntity(
                                id = it.id ?: 0,
                                imageUrl = it.image.orEmpty(),
                                brand = it.brand.toString()
                            ),
                        )
                    }
                }
            }
        }
    }

    suspend fun fetchAndSaveTopProductsFromFirebase() {
        fetchFromDatabase<TopProductsList>(
            "home/topProducts",
            firebaseDatabase,
        ).collect { data ->
            withContext(Dispatchers.IO) {
                val existingEntries = roomDao.getTopProductsList().associateBy { it.id }
                data?.topProductsList?.map {
                    val existing = existingEntries[it.id]
                    if (existing == null || existing.id != it.id) {
                        roomDao.saveTopProductsList(
                            TopProductsListEntity(
                                address = it.address.orEmpty(),
                                id = it.id ?: 0,
                                imageUrl = Converters().fromImagesList(it.images.orEmpty()),
                                title = it.title.orEmpty(),
                                salePercentage = it.salePercentage ?: 0,
                                saleStartsDate = it.saleStartsDate.orEmpty(),
                                saleEndsDate = it.saleEndsDate.orEmpty(),
                                originalPrice = it.originalPrice ?: 0,
                                priceOnSale = it.priceOnSale ?: 0,
                                sizes = Converters().fromSizesList(it.sizes.orEmpty()),
                                brand = it.brand.toString()
                            ),
                        )
                    }
                }
            }
        }
    }

    fun getNewsInfo() = roomDao.getNewsInfoFlow().map { newsEntityList ->
        newsEntityList.map { entity ->
            NewsItem(
                id = entity.id,
                image = entity.image,
                body = entity.body,
                title = entity.title,
            )
        }
    }

    fun getRecommendations() = roomDao.getHomeRecommendationsFlow().map { recommendations ->
        recommendations.map {
            RecommendationItem(id = it.id, image = it.imageUrl)
        }
    }

    fun getTopProducts() = roomDao.getTopProductsFlow().map { topProducts ->
        topProducts.shuffled().take(AMOUNT_OF_TOP_PRODUCTS_IN_HOME_SCREEN).map {
            TopProductItem(
                id = it.id,
                images = Converters().toImagesList(it.imageUrl),
                title = it.title,
                salePercentage = it.salePercentage,
                saleStartsDate = it.saleStartsDate,
                saleEndsDate = it.saleEndsDate,
                priceOnSale = it.priceOnSale,
                originalPrice = it.originalPrice,
            )
        }
    }

    fun getNewsDetail(id: Int) = roomDao.getNewsDetail(id).map {
        NewsItem(
            id = it.id,
            image = it.image,
            body = it.body,
            title = it.title,
        )
    }
}
