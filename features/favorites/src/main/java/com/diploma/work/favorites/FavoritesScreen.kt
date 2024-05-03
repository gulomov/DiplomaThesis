package com.diploma.work.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import com.google.accompanist.pager.PagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.diploma.work.common.componants.Prices
import com.diploma.work.database.entity.ProductImages
import com.diploma.work.design.composables.IndicatorDots
import com.diploma.work.design.composables.MainHorizontalPager
import com.diploma.work.design.theme.normal100
import com.diploma.work.design.theme.small100
import com.diploma.work.design.theme.small150
import com.diploma.work.design.theme.small50
import com.diploma.work.design.theme.topProductsImageHeightSize
import com.diploma.work.design.theme.topProductsImageWidthSize
import com.diploma.work.navigation.ScreenRoute.PRODUCTION_DETAIL
import com.diploma.work.repository.data.FavoriteProduct
import com.google.accompanist.pager.ExperimentalPagerApi

private const val GRID_CELLS = 2

@Composable
fun FavoritesScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val favoriteProducts by viewModel.favoriteProducts.collectAsState()
    Box(modifier = modifier.fillMaxSize()) {
        if (favoriteProducts.isEmpty()) {
            Image(
                painter = painterResource(id = R.drawable.empty_state),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(normal100)
                    .align(Alignment.Center)
            )
        }
        LazyVerticalGrid(modifier = Modifier, columns = GridCells.Fixed(GRID_CELLS), content = {
            items(favoriteProducts) { favoriteProduct ->
                FavoriteProductItem(
                    product = favoriteProduct,
                    productOnClick = { productId ->
                        val route = PRODUCTION_DETAIL.replace("{productId}", productId)
                        navController.navigate(route)
                    },
                    deleteProductFromFavoritesClicked = {
                        favoriteProduct.id?.let { id ->
                            viewModel.deleteFromFavoriteProducts(
                                id
                            )
                        }
                    },
                )
            }
        })
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun FavoriteProductItem(
    product: FavoriteProduct,
    productOnClick: (String) -> Unit,
    deleteProductFromFavoritesClicked: (Unit) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = { product.id?.let { productOnClick.invoke(it.toString()) } },
        modifier = modifier.padding(normal100),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceTint),
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            FavoriteProductImages(
                productPercentage = product.salePercentage.toString(),
                productImagesList = product.images ?: emptyList(),
                deleteProductFromFavoritesClicked = deleteProductFromFavoritesClicked
            )
            Spacer(modifier = Modifier.height(small100))
            Text(
                text = product.title.toString(),
                modifier = Modifier
                    .padding(small100)
                    .align(Alignment.Start)
            )
            Prices(
                originalPrice = product.originalPrice.toString(),
                priceOnSale = product.priceOnSale.toString(),
                modifier = Modifier.padding(start = small100),
                textColor = Color.White
            )
            Spacer(modifier = Modifier.height(small100))
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun FavoriteProductImages(
    productPercentage: String,
    productImagesList: List<ProductImages>,
    deleteProductFromFavoritesClicked: (Unit) -> Unit,
    pagerState: PagerState = remember { PagerState() },
) {
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    Box {
        MainHorizontalPager(
            pagerState = pagerState,
            itemsCount = productImagesList.size,
            itemContent = {
                ProductImage(productImagesList[it].imageUrl)
            },
        )
        Text(
            text = stringResource(
                id = com.diploma.work.common.R.string.topProductsPercentage,
                productPercentage
            ),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = small50, end = small50)
                .background(
                    color = MaterialTheme.colorScheme.error,
                    shape = RoundedCornerShape(topEnd = small100),
                )
                .padding(small50),
        )
        Image(
            painter = painterResource(R.drawable.ic_bookmark),
            contentDescription = "Favorite",
            modifier = Modifier
                .padding(end = small50, bottom = small50)
                .clickable(onClick = { deleteProductFromFavoritesClicked.invoke(Unit) })
                .align(Alignment.BottomEnd)
        )
        Surface(
            modifier = Modifier
                .padding(small100)
                .align(Alignment.BottomCenter),
            shape = CircleShape,
        ) {
            IndicatorDots(
                totalDots = productImagesList.size,
                selectedIndex = if (isDragged) {
                    pagerState.currentPage
                } else pagerState.targetPage,
                dotSize = small100,
            )
        }
    }
}

@Composable
internal fun ProductImage(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(topProductsImageWidthSize)
            .height(topProductsImageHeightSize)
            .padding(small50)
            .clip(RoundedCornerShape(small100)),
    )
}