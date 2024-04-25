package com.diploa.work.prdoductdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.diploma.work.database.entity.ProductImages
import com.diploma.work.design.composables.MainHorizontalPager
import com.diploma.work.design.theme.newsCarouselImageSize
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.common.collect.ImmutableList
import okhttp3.internal.toImmutableList

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductDetails(
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = hiltViewModel(),
) {
    val productDetails by viewModel.productDetails.collectAsState()

    productDetails.images?.let { data ->
        Column(modifier = modifier.fillMaxWidth()) {
            ProductDetailsImages(
                productImages = data
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductDetailsImages(
    productImages: List<ProductImages>,
    pagerState: PagerState = remember { PagerState() },
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        MainHorizontalPager(
            pagerState = pagerState,
            itemsCount = productImages.size,
            itemContent = {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(productImages[it].imageUrl)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(newsCarouselImageSize)
                        .align(Alignment.Center)
                )
            }
        )
    }
}