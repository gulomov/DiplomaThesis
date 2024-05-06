package com.diploma.work.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diploma.work.common.domain.DeleteFromFavoriteProductsUseCase
import com.diploma.work.gallery.domain.FetchAllProductsFromFirebaseAndSaveUseCase
import com.diploma.work.gallery.domain.FetchBrandsFromFirebaseAndSaveUseCase
import com.diploma.work.gallery.domain.GetAllProductsUseCase
import com.diploma.work.gallery.domain.GetBrandsUseCase
import com.diploma.work.repository.data.AllProductsItem
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
    private val deleteFromFavoriteProductsUseCase: DeleteFromFavoriteProductsUseCase,
    private val getBrandsUseCase: GetBrandsUseCase,
    private val getAllProductsUseCase: GetAllProductsUseCase
) : ViewModel() {
    private val _brandsList = MutableStateFlow(listOf(BrandsItem()))
    val brandsList = _brandsList.asStateFlow()

    private val _products = MutableStateFlow(listOf(AllProductsItem()))
    val products = _products.asStateFlow()

    init {
        fetchAllProducts()
        fetchBrands()
    }

    fun getBrands() = viewModelScope.launch {
        getBrandsUseCase().collect {
            if (it.isNotEmpty()) {
                _brandsList.value = it
            } else {
                Timber.e("Brands is empty")
            }
        }
    }

    fun getAllProducts() = viewModelScope.launch {
        getAllProductsUseCase().collect {
            if (it.isNotEmpty()) {
                _products.value = it
            }else{
                Timber.e("Products list is empty")
            }
        }
    }

    private fun fetchAllProducts() = viewModelScope.launch {
        fetchAllProductsFromFirebaseAndSaveUseCase()
    }

    private fun fetchBrands() = viewModelScope.launch {
        fetchBrandsFromFirebaseAndSaveUseCase()
    }

    fun deleteFromFavoriteProducts(productId: Int) = viewModelScope.launch {
        deleteFromFavoriteProductsUseCase(productId)
    }
}