package com.diploma.work.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diploma.work.gallery.domain.FetchAllProductsFromFirebaseAndSaveUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryScreenViewModel @Inject constructor(
    fetchAllProductsFromFirebaseAndSaveUseCase: FetchAllProductsFromFirebaseAndSaveUseCase
) : ViewModel() {
    init {
        viewModelScope.launch {
            fetchAllProductsFromFirebaseAndSaveUseCase()
        }
    }
}