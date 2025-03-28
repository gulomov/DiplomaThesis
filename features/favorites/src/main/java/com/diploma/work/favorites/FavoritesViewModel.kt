package com.diploma.work.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diploma.work.common.domain.DeleteFromFavoriteProductsUseCase
import com.diploma.work.favorites.domain.GetFavoriteProductsUseCase
import com.diploma.work.navigation.ScreenRoute.PRODUCTION_DETAIL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    getFavoriteProductsUseCase: GetFavoriteProductsUseCase,
    private val deleteFromFavoriteProductsUseCase: DeleteFromFavoriteProductsUseCase
) : ViewModel() {
    val uiState = MutableStateFlow(FavoriteScreenUiState())

    private val navigateRoute = MutableStateFlow<String?>(null)

    init {
        viewModelScope.launch {
            combine(
                getFavoriteProductsUseCase(),
                navigateRoute
            ) { favoriteProducts, navigateRoute ->
                FavoriteScreenUiState(
                    favoriteProducts = favoriteProducts,
                    navigateRoute = navigateRoute
                )
            }.collect {
                uiState.value = it
            }

            getFavoriteProductsUseCase().collect {
                uiState.value = FavoriteScreenUiState(
                    favoriteProducts = it,
                    loadingValue = false
                )
            }
        }
    }

    fun onProductClicked(productId: Int) {
        val route = PRODUCTION_DETAIL.replace("{productId}", productId.toString())
        navigateRoute.value = route
    }

    fun resetNavigateRoute() {
        navigateRoute.value = null
    }

    fun deleteFromFavoriteProducts(productId: Int) = viewModelScope.launch {
        deleteFromFavoriteProductsUseCase(productId)
    }
}