package com.diploma.work.gallery

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.diploma.work.common.componants.ProgressCircle
import com.diploma.work.design.theme.normal100
import com.diploma.work.gallery.components.BrandsInGallery
import com.diploma.work.gallery.components.ProductsInGallery

@Composable
fun GalleryScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: GalleryScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.navigationRoute) {
        uiState.navigationRoute?.let { route ->
            navController.navigate(route)
            viewModel.resetNavigate()
        }
    }

    AnimatedContent(targetState = uiState.loadingValue) {
        if (it) {
            ProgressCircle()
        } else {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(normal100)
            ) {
                BrandsInGallery(
                    brandsList = uiState.brands,
                    brandClick = viewModel::loadProductsByBrands
                )
                ProductsInGallery(
                    brands = uiState.brands,
                    products = uiState.products,
                    favoriteIds = uiState.favoriteIds,
                    onProductClick = viewModel::onProductClicked,
                    onDeleteFromFavoriteProducts = viewModel::deleteFromFavoriteProducts,
                    onSaveToFavoriteProduct = viewModel::saveToFavoriteProduct,
                )
            }
        }
    }
}

