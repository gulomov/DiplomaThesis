package com.diploa.work.prdoductdetail.composables

import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.diploma.work.database.entity.ProductImages
import com.diploma.work.design.composables.IndicatorDots
import com.diploma.work.design.composables.MainHorizontalPager
import com.diploma.work.design.theme.productsCarouselImageSize
import com.diploma.work.design.theme.small100
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun ProductDetailsImages(
    productImages: List<ProductImages>,
    pagerState: PagerState = remember { PagerState() },
) {
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    Box(modifier = Modifier.fillMaxWidth()) {
        MainHorizontalPager(
            pagerState = pagerState,
            itemsCount = productImages.size,
            itemContent = {
                AsyncImage(
                    model =
                        ImageRequest.Builder(LocalContext.current)
                            .data(productImages[it].imageUrl)
                            .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier =
                        Modifier
                            .height(productsCarouselImageSize)
                            .align(Alignment.Center),
                )
            },
        )
        Surface(
            modifier =
                Modifier
                    .padding(small100)
                    .align(Alignment.BottomCenter),
            shape = CircleShape,
        ) {
            IndicatorDots(
                totalDots = productImages.size,
                selectedIndex = if (isDragged) pagerState.currentPage else pagerState.targetPage,
                dotSize = small100,
            )
        }
    }
}
