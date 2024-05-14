package com.diploma.work.prdoductdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diploma.work.prdoductdetail.domain.GetProductDetailsUseCase
import com.diploma.work.common.domain.DeleteFromFavoriteProductsUseCase
import com.diploma.work.common.domain.GetTopProductsUseCase
import com.diploma.work.common.domain.IsProductInFavoritesUseCase
import com.diploma.work.common.domain.SaveToFavoriteProductUseCase
import com.diploma.work.repository.data.ProductDetailsData
import com.diploma.work.repository.data.TopProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

private const val PRODUCT_ID = "productId"

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getProductDetailsUseCase: GetProductDetailsUseCase,
    private val isProductInFavoritesUseCase: IsProductInFavoritesUseCase,
    private val getTopProductsUseCase: GetTopProductsUseCase,
    private val saveToFavoriteProductUseCase: SaveToFavoriteProductUseCase,
    private val deleteFromFavoriteProductsUseCase: DeleteFromFavoriteProductsUseCase,
) : ViewModel() {
    private val _productDetail = MutableStateFlow(ProductDetailsData())
    val productDetails = _productDetail.asStateFlow()

    private val _topProductsList = MutableStateFlow(listOf<TopProductItem>())
    val topProductsList = _topProductsList.asStateFlow()

    private val _isProductInFavorites = MutableStateFlow<Boolean?>(null)
    val isProductInFavorites = _isProductInFavorites.asStateFlow()

    private val _startBookingLogic = MutableStateFlow(false)
    val startBookingLogic = _startBookingLogic.asStateFlow()

    init {
        viewModelScope.launch {
            getProductDetailsUseCase(checkNotNull(savedStateHandle[PRODUCT_ID])).collect {
                _productDetail.value = it
            }

        }
        checkProductIsFavorite(checkNotNull(savedStateHandle[PRODUCT_ID]))
    }

    private fun checkProductIsFavorite(productId: String) {
        viewModelScope.launch {
            _isProductInFavorites.value = isProductInFavoritesUseCase(productId.toInt())
        }
    }

    fun getTopProductsList() {
        viewModelScope.launch {
            getTopProductsUseCase().collect { data ->
                if (data.isNotEmpty()) {
                    _topProductsList.value = data
                } else {
                    Timber.e("Top products is empty")
                }
            }
        }
    }

    fun saveProductToFavorites(productItem: ProductDetailsData) = viewModelScope.launch {
        saveToFavoriteProductUseCase(productItem.asFavoriteProduct())
    }

    fun deleteFromFavoriteProducts(productId: Int) = viewModelScope.launch {
        deleteFromFavoriteProductsUseCase(productId)
    }

    fun startBookingLogic(shouldShowBookingLogic: Boolean) {
        _startBookingLogic.value = shouldShowBookingLogic
    }

}
