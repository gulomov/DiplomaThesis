package com.diploma.work.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diploma.work.home.domain.FetchNewsFromFirebaseAndSaveUseCase
import com.diploma.work.home.domain.FetchRecommendationsFromFirebaseAndSaveUseCase
import com.diploma.work.home.domain.GetHomeRecommendationsUseCase
import com.diploma.work.home.domain.GetHomeScreenNewsUseCase
import com.diploma.work.repository.data.NewsItem
import com.diploma.work.repository.data.RecommendationItem
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
    private val getHomeNewsInfoUseCase: GetHomeScreenNewsUseCase,
    private val getHomeRecommendationsUseCase: GetHomeRecommendationsUseCase

) : ViewModel() {
    private val _newsInfo = MutableStateFlow(listOf<NewsItem>())
    val news = _newsInfo.asStateFlow()

    private val _recommendationsList = MutableStateFlow(listOf<RecommendationItem>())
    val recommendationsList = _recommendationsList.asStateFlow()

    init {
        fetchAndSaveNews()
        fetchAndSaveRecommendations()
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

    private fun fetchAndSaveNews() = viewModelScope.launch {
        fetchNewsFromFirebaseAndSaveUseCase()

    }

    private fun fetchAndSaveRecommendations() = viewModelScope.launch {
        fetchRecommendationsFromFirebaseAndSaveUseCase()
    }
}

