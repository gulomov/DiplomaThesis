package com.diploma.work.repository.repository

import com.diploma.work.repository.data.NewsInfo
import com.diploma.work.repository.data.RecommendationsList
import com.diploma.work.repository.generic.fetchFromDatabase
import com.diploma.work.repository.resource.Resource
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val database: FirebaseDatabase
) {
    fun getNewsInfo(): Flow<Resource<NewsInfo>> = fetchFromDatabase("home/news", database)

    fun getRecommendations(): Flow<Resource<RecommendationsList>> =
        fetchFromDatabase("home/recommendations", database)
}