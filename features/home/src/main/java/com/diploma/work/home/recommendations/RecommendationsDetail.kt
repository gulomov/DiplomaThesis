package com.diploma.work.home.recommendations

import GenericProductItem
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.diploma.work.design.theme.GRID_CELLS
import com.diploma.work.design.theme.normal100
import com.diploma.work.design.theme.small100
import com.diploma.work.navigation.ScreenRoute

@Composable
fun RecommendationsDetail(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: RecommendationsDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = modifier) {
        LazyVerticalGrid(
            modifier = Modifier,
            columns = GridCells.Fixed(GRID_CELLS),
            verticalArrangement = Arrangement.spacedBy(small100),
            horizontalArrangement = Arrangement.spacedBy(small100),
            contentPadding = PaddingValues(normal100),
            content = {
                items(uiState.products) { product ->
                    GenericProductItem(
                        item = product,
                        onClick = {
                            val route = ScreenRoute.PRODUCTION_DETAIL.replace(
                                "{productId}",
                                it.id.toString()
                            )
                            navController.navigate(route)
                        },
                        onSaveOrDeleteClick = {
                            if (!it) {
                                product.id?.let { id ->
                                    viewModel.deleteFromFavoriteProducts(id)
                                }
                            } else {
                                viewModel.saveToFavoriteProduct(product)
                            }
                        },
                        productImagesList = product.images?.map { it.imageUrl } ?: emptyList(),
                        productPercentage = product.salePercentage.toString(),
                        title = product.title.toString(),
                        originalPrice = product.originalPrice.toString(),
                        priceOnSale = product.priceOnSale.toString(),
                        isFavorite = uiState.favoriteIds.contains(product.id)
                    )
                }
            })
    }
}