package com.diploma.work.gallery

import GenericProductItem
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.diploma.work.common.componants.EmptyStateImage
import com.diploma.work.design.theme.GRID_CELLS
import com.diploma.work.design.theme.normal100
import com.diploma.work.gallery.components.BrandsInGallery
import com.diploma.work.navigation.ScreenRoute.PRODUCTION_DETAIL

@Composable
fun GalleryScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: GalleryScreenViewModel = hiltViewModel()
) {
    val brands by viewModel.brandsList.collectAsState()
    val products by viewModel.products.collectAsState()
    val favoriteIds by viewModel.favoriteIds.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getBrands()
        viewModel.getAllProducts()
        viewModel.getFavoriteProductsIds()
    }
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier,
    ) {
        BrandsInGallery(brands)
        Spacer(modifier = Modifier.height(normal100))
        Box {
            if (brands.isEmpty()) {
                EmptyStateImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(normal100)
                        .align(Alignment.Center)
                )
            }
            LazyVerticalGrid(modifier = Modifier, columns = GridCells.Fixed(GRID_CELLS), content = {
                items(products) { product ->
                    GenericProductItem(
                        item = product,
                        onClick = {
                            val route = PRODUCTION_DETAIL.replace("{productId}", it.id.toString())
                            navController.navigate(route)
                        },
                        onDeleteClick = {
                            product.id?.let { id ->
                                viewModel.deleteFromFavoriteProducts(id)
                            }
                            // FIXME: Implement logic for adding/removing to/from database
                        },
                        productImagesList = product.images?.map { it.imageUrl } ?: emptyList(),
                        productPercentage = product.salePercentage.toString(),
                        title = product.title.toString(),
                        originalPrice = product.originalPrice.toString(),
                        priceOnSale = product.priceOnSale.toString(),
                        isFavorite = favoriteIds.contains(product.id)
                    )
                }
            })
        }
    }
}