package com.diploma.work.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diploma.work.home.domain.FetchNewsFromFirebaseAndSaveUseCase
import com.diploma.work.home.domain.GetHomeRecommendationsUseCase
import com.diploma.work.home.domain.GetHomeScreenNewsUseCase
import com.diploma.work.repository.data.NewsInfo
import com.diploma.work.repository.data.NewsItem
import com.diploma.work.repository.data.RecommendationItem
import com.diploma.work.repository.data.RecommendationsList
import com.diploma.work.repository.repository.HomeRepository
import com.diploma.work.repository.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchNewsFromFirebaseAndSaveUseCase: FetchNewsFromFirebaseAndSaveUseCase,
    private val getHomeNewsInfoUseCase: GetHomeScreenNewsUseCase,
    private val getHomeRecommendationsUseCase: GetHomeRecommendationsUseCase

) : ViewModel() {
    private val _newsInfo = MutableStateFlow(listOf<NewsItem>())
    val news = _newsInfo.asStateFlow()

    private val _recommendationsList = MutableStateFlow(listOf<RecommendationItem>())
    val recommendationsList = _recommendationsList.asStateFlow()

    init {
        viewModelScope.launch {
            fetchNewsFromFirebaseAndSaveUseCase()
        }
    }

    fun getNews() {
        viewModelScope.launch {
            getHomeNewsInfoUseCase().collect { resource ->
                if (resource.isNotEmpty()) {
                    println("Success in news viewmodel: $resource")
                    _newsInfo.value = resource
                } else {
                    println("Error in ViewModel")
                }

            }
        }
    }

    fun getRecommendationsList() {
        viewModelScope.launch {
            getHomeRecommendationsUseCase().collect { resource ->
                if (resource.isNotEmpty()) {
                    println("Success in recommendations viewmodel: $resource")
                    _recommendationsList.value = resource
                } else {
                    println("Error in ViewModel")
                }
            }
        }
    }
}

