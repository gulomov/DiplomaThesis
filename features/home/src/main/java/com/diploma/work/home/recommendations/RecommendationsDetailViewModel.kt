package com.diploma.work.home.recommendations

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diploma.work.common.domain.DeleteFromFavoriteProductsUseCase
import com.diploma.work.common.domain.GetFavoriteProductsIdsUseCase
import com.diploma.work.common.domain.GetProductsByBrandNameUseCase
import com.diploma.work.common.domain.SaveToFavoriteProductUseCase
import com.diploma.work.repository.data.AllProductsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val BRAND_NAME = "brandName"

@HiltViewModel
class RecommendationsDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getProductsByBrandNameUseCase: GetProductsByBrandNameUseCase,
    private val deleteFromFavoriteProductsUseCase: DeleteFromFavoriteProductsUseCase,
    private val saveToFavoriteProductUseCase: SaveToFavoriteProductUseCase,
    private val getFavoriteProductsIdsUseCase: GetFavoriteProductsIdsUseCase,
) : ViewModel() {
    private val _products = MutableStateFlow(listOf(AllProductsItem()))
    val products: StateFlow<List<AllProductsItem>> = _products

    private val _favoriteIds = MutableStateFlow(listOf<Int>())
    val favoriteIds = _favoriteIds.asStateFlow()

    init {
        viewModelScope.launch {
            _products.value =
                getProductsByBrandNameUseCase(checkNotNull(savedStateHandle[BRAND_NAME]))
            getFavoriteProductsIds()
        }
    }

    fun deleteFromFavoriteProducts(productId: Int) = viewModelScope.launch {
        deleteFromFavoriteProductsUseCase(productId)
    }

    fun saveToFavoriteProduct(product: AllProductsItem) = viewModelScope.launch {
        saveToFavoriteProductUseCase(product.asFavoriteProduct())
    }

    fun getFavoriteProductsIds() {
        viewModelScope.launch {
            _favoriteIds.value = getFavoriteProductsIdsUseCase()
        }
    }
}