package com.diploma.work.home.news

import android.view.View
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diploma.work.home.domain.GetNewsDetailUseCase
import com.diploma.work.repository.data.NewsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val NEWS_ID = "newsId"

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getNewsDetailUseCase: GetNewsDetailUseCase
) : ViewModel() {

    private val _details = MutableStateFlow(NewsItem())
    val details = _details.asStateFlow()

    init {
        viewModelScope.launch {
            getNewsDetailUseCase(checkNotNull(savedStateHandle[NEWS_ID])).collect {
                _details.value = it
            }
        }
    }
}