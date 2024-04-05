package com.diploma.work.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diploma.work.repository.data.NewsInfo
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
    private val repository: HomeRepository
) : ViewModel() {
    private val _newsInfo = MutableStateFlow(NewsInfo())
    val news = _newsInfo.asStateFlow()

    private val _recommendationsList = MutableStateFlow(RecommendationsList())
    val recommendationsList = _recommendationsList.asStateFlow()

    fun getNews() {
        viewModelScope.launch {
            repository.getNewsInfo().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        resource.data?.let {
                            _newsInfo.value = it
                        }
                    }

                    is Resource.Error -> {
                        println("Error is ${resource.data}")
                    }
                }
            }
        }
    }

    fun getRecommendationsList() {
        viewModelScope.launch {
            repository.getRecommendations().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        resource.data?.let {
                            println("recommendations: $it")
                            _recommendationsList.value = it
                        }
                    }

                    is Resource.Error -> {
                        println("Error is ${resource.data}")
                    }
                }
            }
        }
    }
}

