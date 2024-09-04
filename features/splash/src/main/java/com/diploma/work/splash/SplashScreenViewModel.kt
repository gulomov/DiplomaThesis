package com.diploma.work.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diploma.work.common.domain.FetchAllProductsFromFirebaseAndSaveUseCase
import com.diploma.work.common.domain.FetchComingSoonProductsUseCase
import com.diploma.work.common.domain.FetchNewsFromFirebaseAndSaveUseCase
import com.diploma.work.common.domain.FetchRecommendationsFromFirebaseAndSaveUseCase
import com.diploma.work.common.domain.FetchTopProductsFromFirebaseAndSaveUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val fetchNewsFromFirebaseAndSaveUseCase: FetchNewsFromFirebaseAndSaveUseCase,
    private val fetchRecommendationsFromFirebaseAndSaveUseCase: FetchRecommendationsFromFirebaseAndSaveUseCase,
    private val fetchTopProductsFromFirebaseAndSaveUseCase: FetchTopProductsFromFirebaseAndSaveUseCase,
    private val fetchAllProductsFromFirebaseAndSaveUseCase: FetchAllProductsFromFirebaseAndSaveUseCase,
    private val fetchComingSoonProductsUseCase: FetchComingSoonProductsUseCase,
    private val splashScreenRepository: SplashScreenRepository,
) : ViewModel() {
    init {
        fetchNews()
        fetchRecommendations()
        fetchTopProducts()
        fetchAllProducts()
        fetchComingSoonProducts()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            fetchNewsFromFirebaseAndSaveUseCase()
        }
    }

    private fun fetchAllProducts() {
        viewModelScope.launch {
            fetchAllProductsFromFirebaseAndSaveUseCase()
        }
    }

    private fun fetchRecommendations() {
        viewModelScope.launch {
            fetchRecommendationsFromFirebaseAndSaveUseCase()
        }
    }

    private fun fetchTopProducts() {
        viewModelScope.launch {
            fetchTopProductsFromFirebaseAndSaveUseCase()
        }
    }

    private fun fetchComingSoonProducts() {
        viewModelScope.launch {
            fetchComingSoonProductsUseCase()
        }
    }

    fun wasIntroductionShown() = splashScreenRepository.wasIntroductionShown()
}
