package com.diploma.work.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diploma.work.gallery.domain.FetchAllProductsFromFirebaseAndSaveUseCase
import com.diploma.work.gallery.domain.FetchBrandsFromFirebaseAndSaveUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryScreenViewModel @Inject constructor(
    private val fetchAllProductsFromFirebaseAndSaveUseCase: FetchAllProductsFromFirebaseAndSaveUseCase,
    private val fetchBrandsFromFirebaseAndSaveUseCase: FetchBrandsFromFirebaseAndSaveUseCase
) : ViewModel() {
    init {
        fetchAllProducts()
        fetchBrands()
    }

    private fun fetchAllProducts() = viewModelScope.launch {
        fetchAllProductsFromFirebaseAndSaveUseCase()
    }

    private fun fetchBrands() = viewModelScope.launch {
        fetchBrandsFromFirebaseAndSaveUseCase()
    }
}