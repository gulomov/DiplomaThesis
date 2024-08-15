package com.diploma.work.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diploma.work.search.domain.SearchProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val searchProductUseCase: SearchProductUseCase
) : ViewModel() {
    val uiState = MutableStateFlow(SearchUiState())

    fun onSearch(searchQuery: String) = viewModelScope.launch {
        flowOf(searchProductUseCase(searchQuery)).collect {
            it.firstOrNull()?.let { allProducts ->
                uiState.value = SearchUiState(products = allProducts, loadingValue = false)
            }
        }
    }

}