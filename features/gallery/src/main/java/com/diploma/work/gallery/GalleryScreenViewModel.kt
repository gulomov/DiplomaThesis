package com.diploma.work.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diploma.work.gallery.domain.FetchAllProductsFromFirebaseAndSaveUseCase
import com.diploma.work.gallery.domain.FetchBrandsFromFirebaseAndSaveUseCase
import com.diploma.work.gallery.domain.GetBrandsUseCase
import com.diploma.work.repository.data.BrandsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GalleryScreenViewModel @Inject constructor(
    private val fetchAllProductsFromFirebaseAndSaveUseCase: FetchAllProductsFromFirebaseAndSaveUseCase,
    private val fetchBrandsFromFirebaseAndSaveUseCase: FetchBrandsFromFirebaseAndSaveUseCase,
    private val getBrandsUseCase: GetBrandsUseCase,
) : ViewModel() {
    private val _brandsList = MutableStateFlow(listOf(BrandsItem()))
    val brandsList = _brandsList.asStateFlow()

    init {
        fetchAllProducts()
        fetchBrands()
    }

    fun getBrands() {
        viewModelScope.launch {
            getBrandsUseCase().collect {
                if (it.isNotEmpty()) {
                    _brandsList.value = it
                }else{
                    Timber.e("Brands is empty")
                }
            }
        }
    }

    private fun fetchAllProducts() = viewModelScope.launch {
        fetchAllProductsFromFirebaseAndSaveUseCase()
    }

    private fun fetchBrands() = viewModelScope.launch {
        fetchBrandsFromFirebaseAndSaveUseCase()
    }
}