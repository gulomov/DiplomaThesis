package com.diploma.work.home

import com.diploma.work.repository.data.NewsItem
import com.diploma.work.repository.data.RecommendationItem
import com.diploma.work.repository.data.TopProductItem

data class HomeScreenUiState(
    val newsInfo: List<NewsItem> = emptyList(),
    val recommendationsList: List<RecommendationItem> = emptyList(),
    val topProductsList: List<TopProductItem> = emptyList(),
    val loadingValue: Boolean = true
)