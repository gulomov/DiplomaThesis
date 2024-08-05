package com.diploma.work.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diploma.work.common.domain.DeleteFromFavoriteProductsUseCase
import com.diploma.work.common.domain.GetFavoriteProductsIdsUseCase
import com.diploma.work.common.domain.SaveToFavoriteProductUseCase
import com.diploma.work.common.domain.FetchAllProductsFromFirebaseAndSaveUseCase
import com.diploma.work.gallery.domain.FetchBrandsFromFirebaseAndSaveUseCase
import com.diploma.work.gallery.domain.GetAllProductsUseCase
import com.diploma.work.gallery.domain.GetBrandsUseCase
import com.diploma.work.common.domain.GetProductsByBrandNameUseCase
import com.diploma.work.repository.data.AllProductsItem
import com.diploma.work.repository.data.BrandsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GalleryScreenViewModel @Inject constructor(
    private val fetchAllProductsFromFirebaseAndSaveUseCase: FetchAllProductsFromFirebaseAndSaveUseCase,
    private val fetchBrandsFromFirebaseAndSaveUseCase: FetchBrandsFromFirebaseAndSaveUseCase,
    private val deleteFromFavoriteProductsUseCase: DeleteFromFavoriteProductsUseCase,
    private val getFavoriteProductsIdsUseCase: GetFavoriteProductsIdsUseCase,
    private val getBrandsUseCase: GetBrandsUseCase,
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getProductsByBrandNameUseCase: GetProductsByBrandNameUseCase,
    private val saveToFavoriteProductUseCase: SaveToFavoriteProductUseCase
) : ViewModel() {
    val uiState = MutableStateFlow(GalleryScreenUiState())
    private val brandsList = MutableStateFlow(listOf(BrandsItem()))
    private val products = MutableStateFlow(listOf(AllProductsItem()))
    private val favoriteIds = MutableStateFlow(listOf<Int>())

    init {
        fetchAllProducts()
        fetchBrands()
        getBrands()
        getAllProducts()
        getFavoriteProductsIds()
        combineFlow()
    }

    private fun combineFlow() {
        viewModelScope.launch {
            combine(
                brandsList,
                products,
                favoriteIds
            ) { brands, products, favoriteIds ->
                GalleryScreenUiState(
                    brands = brands,
                    products = products,
                    favoriteIds = favoriteIds,
                    loadingValue = false,
                )
            }.collect {
                uiState.value = it
            }
        }
    }

    private fun getBrands() = viewModelScope.launch {
        getBrandsUseCase().collect {
            if (it.isNotEmpty()) {
                brandsList.value = it
            } else {
                Timber.e("Brands is empty")
            }
        }
    }

    private fun getAllProducts() = viewModelScope.launch {
        getAllProductsUseCase().collect {
            if (it.isNotEmpty()) {
                products.value = it
            } else {
                Timber.e("Products list is empty")
            }
        }
    }

    fun loadProductsByBrands(brandName: String) = viewModelScope.launch {
        getProductsByBrandNameUseCase(brandName).apply {
            if (this.isEmpty()) {
                getAllProducts()
            } else {
                products.value = this
            }
        }
        getFavoriteProductsIds()
    }

    private fun getFavoriteProductsIds() {
        viewModelScope.launch {
            getFavoriteProductsIdsUseCase().collect {
                favoriteIds.value = it
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

    fun saveToFavoriteProduct(product: AllProductsItem) = viewModelScope.launch {
        saveToFavoriteProductUseCase(product.asFavoriteProduct())
    }
}