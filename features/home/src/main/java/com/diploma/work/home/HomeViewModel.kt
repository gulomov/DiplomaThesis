package com.diploma.work.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diploma.work.home.domain.FetchNewsFromFirebaseAndSaveUseCase
import com.diploma.work.home.domain.FetchRecommendationsFromFirebaseAndSaveUseCase
import com.diploma.work.home.domain.FetchTopProductsFromFirebaseAndSaveUseCase
import com.diploma.work.home.domain.GetHomeRecommendationsUseCase
import com.diploma.work.home.domain.GetHomeScreenNewsUseCase
import com.diploma.work.home.domain.GetHomeTopProductsUseCase
import com.diploma.work.repository.data.NewsItem
import com.diploma.work.repository.data.RecommendationItem
import com.diploma.work.repository.data.TopProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchNewsFromFirebaseAndSaveUseCase: FetchNewsFromFirebaseAndSaveUseCase,
    private val fetchRecommendationsFromFirebaseAndSaveUseCase: FetchRecommendationsFromFirebaseAndSaveUseCase,
    private val fetchTopProductsFromFirebaseAndSaveUseCase: FetchTopProductsFromFirebaseAndSaveUseCase,
    private val getHomeNewsInfoUseCase: GetHomeScreenNewsUseCase,
    private val getHomeRecommendationsUseCase: GetHomeRecommendationsUseCase,
    private val getHomeTopProductsUseCase: GetHomeTopProductsUseCase

) : ViewModel() {
    private val _newsInfo = MutableStateFlow(listOf<NewsItem>())
    val news = _newsInfo.asStateFlow()

    private val _recommendationsList = MutableStateFlow(listOf<RecommendationItem>())
    val recommendationsList = _recommendationsList.asStateFlow()

    private val _topProductsList = MutableStateFlow(listOf<TopProductItem>())
    val topProductsList = _topProductsList.asStateFlow()

    init {
        fetchAndSaveNews()
        fetchAndSaveRecommendations()
        fetchAndSaveTopProducts()
    }

    fun getNews() {
        viewModelScope.launch {
            getHomeNewsInfoUseCase().collect { resource ->
                if (resource.isNotEmpty()) {
                    _newsInfo.value = resource
                } else {
                    Timber.e("News is empty")
                }

            }
        }
    }

    fun getRecommendationsList() {
        viewModelScope.launch {
            getHomeRecommendationsUseCase().collect { resource ->
                if (resource.isNotEmpty()) {
                    _recommendationsList.value = resource
                } else {
                    Timber.e("Recommendations is empty")
                }
            }
        }
    }

    fun getTopProductsList() {
        viewModelScope.launch {
            getHomeTopProductsUseCase().collect { data ->
                if (data.isNotEmpty()) {
                    _topProductsList.value = data
                } else {
                    Timber.e("Top products is empty")
                }
            }
        }
    }

    private fun fetchAndSaveNews() = viewModelScope.launch {
        fetchNewsFromFirebaseAndSaveUseCase()

    }

    private fun fetchAndSaveRecommendations() = viewModelScope.launch {
        fetchRecommendationsFromFirebaseAndSaveUseCase()
    }

    private fun fetchAndSaveTopProducts() = viewModelScope.launch {
        fetchTopProductsFromFirebaseAndSaveUseCase()
    }
}

