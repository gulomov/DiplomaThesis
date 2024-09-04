package com.diploma.work.comingsoon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diploma.work.comingsoon.domain.GetComingSoonProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComingSoonViewModel @Inject constructor(
    getComingSoonProductsUseCase: GetComingSoonProductsUseCase
) : ViewModel() {

    val uiState = MutableStateFlow(ComingSoonUiState())

    init {
        viewModelScope.launch {
            getComingSoonProductsUseCase().collect {
                uiState.value = ComingSoonUiState(it)
            }
        }
    }
}
