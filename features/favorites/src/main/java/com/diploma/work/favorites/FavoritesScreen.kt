package com.diploma.work.favorites

import GenericProductItem
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.diploma.work.common.componants.EmptyStateImage
import com.diploma.work.design.theme.DiplomaThesisTheme
import com.diploma.work.design.theme.GRID_CELLS
import com.diploma.work.design.theme.normal100
import com.diploma.work.design.theme.small100
import com.diploma.work.navigation.ScreenRoute.PRODUCTION_DETAIL

@Composable
fun FavoritesScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    Box(modifier = modifier.fillMaxSize()) {
        if (uiState.favoriteProducts.isEmpty()) {
            EmptyStateImage(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(normal100)
                    .align(Alignment.Center)
            )
        }
        LazyVerticalGrid(
            modifier = Modifier,
            columns = GridCells.Fixed(GRID_CELLS),
            verticalArrangement = Arrangement.spacedBy(small100),
            horizontalArrangement = Arrangement.spacedBy(small100),
            contentPadding = PaddingValues(normal100),
            content = {
                items(uiState.favoriteProducts) { favoriteProduct ->
                    GenericProductItem(
                        item = favoriteProduct,
                        onClick = {
                            val route = PRODUCTION_DETAIL.replace("{productId}", it.id.toString())
                            navController.navigate(route)
                        },
                        onSaveOrDeleteClick = {
                            favoriteProduct.id?.let { id ->
                                viewModel.deleteFromFavoriteProducts(
                                    id
                                )
                            }
                        },
                        productImagesList = favoriteProduct.images?.map { it.imageUrl }
                            ?: emptyList(),
                        productPercentage = favoriteProduct.salePercentage.toString(),
                        title = favoriteProduct.title.toString(),
                        originalPrice = favoriteProduct.originalPrice.toString(),
                        priceOnSale = favoriteProduct.priceOnSale.toString(),
                        modifier = Modifier,
                        isFavorite = true
                    )
                }
            })
    }
}

@PreviewLightDark
@Composable
private fun FavoritesScreenPreview() {
    DiplomaThesisTheme {
        FavoritesScreen(rememberNavController())
    }
}