package com.diploma.work.prdoductdetail.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.diploma.work.database.entity.ProductImages
import com.diploma.work.design.composables.IndicatorDots
import com.diploma.work.design.composables.MainHorizontalPager
import com.diploma.work.design.composables.ProductHorizontalPager
import com.diploma.work.design.theme.normal175
import com.diploma.work.design.theme.small100
import com.diploma.work.design.theme.small50
import com.diploma.work.prdoductdetail.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import timber.log.Timber

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun ProductDetailsImages(
    productImages: List<ProductImages>,
    pagerState: PagerState = remember { PagerState() },
    saveProductIntoFavoritesClicked: (Boolean) -> Unit,
    isProductSavedIntoFavorites: Boolean?
) {
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    var isProductSaved by remember { mutableStateOf(isProductSavedIntoFavorites ?: false) }
    Timber.d("isProductSaved: $isProductSaved and isProductSavedIntoFavoritesFlow: $isProductSavedIntoFavorites")
    LaunchedEffect(isProductSavedIntoFavorites) {
        isProductSaved = isProductSavedIntoFavorites ?: false
    }
    Box(modifier = Modifier.fillMaxWidth()) {
        MainHorizontalPager(
            pagerState = pagerState,
            itemsCount = productImages.size,
            itemContent = {
                ProductHorizontalPager(productImages[it].imageUrl)
            },
        )
        Box(
            Modifier
                .padding(
                    end = normal175,
                    bottom = small50
                )
                .align(Alignment.BottomEnd)
        ) {
            Image(
                painter = painterResource(
                    id = if (!isProductSaved) {
                        R.drawable.ic_bookmark_border
                    } else {
                        R.drawable.ic_bookmark
                    }
                ),
                contentDescription = "Favorite",
                modifier = Modifier
                    .clickable(onClick = {
                        isProductSaved = isProductSaved == false
                        saveProductIntoFavoritesClicked(isProductSaved)
                    })
            )
        }
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
