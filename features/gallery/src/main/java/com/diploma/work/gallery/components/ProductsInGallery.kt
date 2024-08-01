package com.diploma.work.gallery.components

import GenericProductItem
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.diploma.work.common.componants.EmptyStateImage
import com.diploma.work.database.entity.ProductImages
import com.diploma.work.design.theme.GRID_CELLS
import com.diploma.work.design.theme.normal100
import com.diploma.work.design.theme.small100
import com.diploma.work.navigation.ScreenRoute
import com.diploma.work.repository.data.AllProductsItem
import com.diploma.work.repository.data.BrandsItem

@Composable
fun ProductsInGallery(
    brands: List<BrandsItem>,
    products: List<AllProductsItem>,
    navController: NavController,
    onDeleteFromFavoriteProducts: (Int) -> Unit,
    onSaveToFavoriteProduct: (AllProductsItem) -> Unit,
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
            modifier = Modifier.padding(horizontal = normal100),
            columns = GridCells.Fixed(GRID_CELLS),
            verticalArrangement = Arrangement.spacedBy(small100),
            horizontalArrangement = Arrangement.spacedBy(small100),
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
                                    onDeleteFromFavoriteProducts(id)
                                }
                            } else {
                                onSaveToFavoriteProduct(product)
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

@PreviewLightDark
@Composable
private fun ProductsInGalleryPreview() {
    val brands = listOf(
        BrandsItem(1, "imageUrl", "brand"),
        BrandsItem(1, "imageUrl", "brand"),
        BrandsItem(1, "imageUrl", "brand"),
    )

    val products = listOf(
        AllProductsItem(
            "address", id = 1, listOf(ProductImages(imageUrl = "image")),
            "title", 1, "saleDate"
        ),
        AllProductsItem(
            "address", id = 1, listOf(ProductImages(imageUrl = "image")),
            "title", 1, "saleDate"
        ),
        AllProductsItem(
            "address", id = 1, listOf(ProductImages(imageUrl = "image")),
            "title", 1, "saleDate"
        ),
    )
    ProductsInGallery(
        brands = brands,
        products = products,
        navController = rememberNavController(),
        favoriteIds = listOf(1, 2),
        onDeleteFromFavoriteProducts = {},
        onSaveToFavoriteProduct = {}
    )
}