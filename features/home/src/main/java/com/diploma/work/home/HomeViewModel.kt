package com.diploma.work.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diploma.work.common.domain.FetchAllProductsFromFirebaseAndSaveUseCase
import com.diploma.work.common.domain.GetTopProductsUseCase
import com.diploma.work.common.domain.FetchNewsFromFirebaseAndSaveUseCase
import com.diploma.work.common.domain.FetchRecommendationsFromFirebaseAndSaveUseCase
import com.diploma.work.common.domain.FetchTopProductsFromFirebaseAndSaveUseCase
import com.diploma.work.home.domain.GetHomeRecommendationsUseCase
import com.diploma.work.home.domain.GetHomeScreenNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeNewsInfoUseCase: GetHomeScreenNewsUseCase,
    private val getHomeRecommendationsUseCase: GetHomeRecommendationsUseCase,
    private val getTopProductsUseCase: GetTopProductsUseCase,
) : ViewModel() {
    val uiState = MutableStateFlow(HomeScreenUiState())

    init {
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
                    topProductsList = topProducts,
                    loadingValue = false
                )
            }.collect {
                uiState.value = it
            }
        }
    }
}
