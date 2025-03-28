package com.diploma.work.home.recommendations

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diploma.work.common.domain.DeleteFromFavoriteProductsUseCase
import com.diploma.work.common.domain.GetFavoriteProductsIdsUseCase
import com.diploma.work.common.domain.GetProductsByBrandNameUseCase
import com.diploma.work.common.domain.SaveToFavoriteProductUseCase
import com.diploma.work.navigation.ScreenRoute
import com.diploma.work.repository.data.AllProductsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val BRAND_NAME = "brandName"

@HiltViewModel
class RecommendationsDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getProductsByBrandNameUseCase: GetProductsByBrandNameUseCase,
    getFavoriteProductsIdsUseCase: GetFavoriteProductsIdsUseCase,
    private val deleteFromFavoriteProductsUseCase: DeleteFromFavoriteProductsUseCase,
    private val saveToFavoriteProductUseCase: SaveToFavoriteProductUseCase,
) : ViewModel() {
    val uiState = MutableStateFlow(RecommendationUiState())

    private val navigateRoute = MutableStateFlow<String?>(null)

    init {
        viewModelScope.launch {
            combine(
                flowOf(
                    getProductsByBrandNameUseCase(
                        checkNotNull(savedStateHandle[BRAND_NAME])
                    )
                ),
                getFavoriteProductsIdsUseCase(),
                navigateRoute
            ) { products, favoriteId, navigateRoute ->
                RecommendationUiState(
                    products = products,
                    favoriteIds = favoriteId,
                    navigateRoute = navigateRoute
                )
            }.collect {
                uiState.value = it
            }
        }
    }

    fun onProductClicked(productId: Int) {
        val route = ScreenRoute.PRODUCTION_DETAIL.replace(
            "{productId}",
            productId.toString()
        )
        navigateRoute.value = route
    }

    fun resetNavigate(){
        navigateRoute.value = null
    }

    fun deleteFromFavoriteProducts(productId: Int) = viewModelScope.launch {
        deleteFromFavoriteProductsUseCase(productId)
    }

    fun saveToFavoriteProduct(product: AllProductsItem) = viewModelScope.launch {
        saveToFavoriteProductUseCase(product.asFavoriteProduct())
    }
}