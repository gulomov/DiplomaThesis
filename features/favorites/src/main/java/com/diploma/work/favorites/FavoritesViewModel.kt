package com.diploma.work.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diploma.work.common.domain.DeleteFromFavoriteProductsUseCase
import com.diploma.work.favorites.domain.GetFavoriteProductsUseCase
import com.diploma.work.repository.data.FavoriteProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteProductsUseCase: GetFavoriteProductsUseCase,
    private val deleteFromFavoriteProductsUseCase: DeleteFromFavoriteProductsUseCase
) : ViewModel() {
    private val _favoriteProducts = MutableStateFlow(listOf<FavoriteProduct>())
    val favoriteProducts = _favoriteProducts.asStateFlow()

    init {
        getFavoriteItems()
    }

    private fun getFavoriteItems() = viewModelScope.launch {
        getFavoriteProductsUseCase().collect {
            _favoriteProducts.value = it
        }
    }

    fun deleteFromFavoriteProducts(productId: Int) = viewModelScope.launch {
        deleteFromFavoriteProductsUseCase(productId)
    }
}