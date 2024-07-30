package com.diploma.work.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diploma.work.common.domain.FetchAllProductsFromFirebaseAndSaveUseCase
import com.diploma.work.common.domain.GetTopProductsUseCase
import com.diploma.work.home.domain.FetchNewsFromFirebaseAndSaveUseCase
import com.diploma.work.home.domain.FetchRecommendationsFromFirebaseAndSaveUseCase
import com.diploma.work.home.domain.FetchTopProductsFromFirebaseAndSaveUseCase
import com.diploma.work.home.domain.GetHomeRecommendationsUseCase
import com.diploma.work.home.domain.GetHomeScreenNewsUseCase
import com.diploma.work.repository.data.NewsItem
import com.diploma.work.repository.data.RecommendationItem
import com.diploma.work.repository.data.TopProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchNewsFromFirebaseAndSaveUseCase: FetchNewsFromFirebaseAndSaveUseCase,
    private val fetchRecommendationsFromFirebaseAndSaveUseCase: FetchRecommendationsFromFirebaseAndSaveUseCase,
    private val fetchTopProductsFromFirebaseAndSaveUseCase: FetchTopProductsFromFirebaseAndSaveUseCase,
    private val fetchAllProductsFromFirebaseAndSaveUseCase: FetchAllProductsFromFirebaseAndSaveUseCase,
    private val getHomeNewsInfoUseCase: GetHomeScreenNewsUseCase,
    private val getHomeRecommendationsUseCase: GetHomeRecommendationsUseCase,
    private val getTopProductsUseCase: GetTopProductsUseCase,
) : ViewModel() {
    val uiState = MutableStateFlow(HomeScreenUiState())

    init {
        fetchAndSaveHomeScreenData()
        combineFlow()
    }

    private fun combineFlow() {
        viewModelScope.launch {
            combine(
                getHomeNewsInfoUseCase(),
                getHomeRecommendationsUseCase(),
                getTopProductsUseCase()
            ) { news, recommendations, topProducts ->
                HomeScreenUiState(
                    newsInfo = news,
                    recommendationsList = recommendations,
                    topProductsList = topProducts
                )
            }.collect {
                uiState.value = it
            }
        }
    }

    private fun fetchAndSaveHomeScreenData() = viewModelScope.launch {
        fetchNewsFromFirebaseAndSaveUseCase()
        fetchRecommendationsFromFirebaseAndSaveUseCase()
        fetchTopProductsFromFirebaseAndSaveUseCase()
        fetchAllProductsFromFirebaseAndSaveUseCase()
    }
}
