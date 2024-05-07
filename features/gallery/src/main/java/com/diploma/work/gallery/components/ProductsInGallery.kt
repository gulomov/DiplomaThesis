package com.diploma.work.gallery.components

import GenericProductItem
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.diploma.work.common.componants.EmptyStateImage
import com.diploma.work.design.theme.GRID_CELLS
import com.diploma.work.design.theme.normal100
import com.diploma.work.gallery.GalleryScreenViewModel
import com.diploma.work.navigation.ScreenRoute
import com.diploma.work.repository.data.AllProductsItem
import com.diploma.work.repository.data.BrandsItem

@Composable
fun ProductsInGallery(
    brands: List<BrandsItem>,
    products: List<AllProductsItem>,
    navController: NavController,
    viewModel: GalleryScreenViewModel,
    favoriteIds: List<Int>
) {
    Box {
        if (brands.isEmpty()) {
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
            content = {
                items(products) { product ->
                    GenericProductItem(
                        item = product,
                        onClick = {
                            val route =
                                ScreenRoute.PRODUCTION_DETAIL.replace(
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
                        isFavorite = favoriteIds.contains(product.id)
                    )
                }
            })
    }
}