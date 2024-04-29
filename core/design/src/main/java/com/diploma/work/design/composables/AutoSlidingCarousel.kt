package com.diploma.work.design.composables

import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.diploma.work.design.theme.small100
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.delay

private const val NEXT_PAGE = 1

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AutoSlidingCarousel(
    itemsCount: Int,
    itemContent: @Composable (index: Int) -> Unit,
    modifier: Modifier = Modifier,
    autoSlideDuration: Long = 3000,
    pagerState: PagerState = remember { PagerState() },
) {
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    LaunchedEffect(pagerState.currentPage) {
        delay(autoSlideDuration)
        pagerState.animateScrollToPage(
            page = (pagerState.currentPage + NEXT_PAGE) % itemsCount,
            animationSpec = tween(5000), // FIXME: This should be reconsidered
        )
    }

    Box(modifier = modifier.fillMaxWidth()) {
        MainHorizontalPager(
            pagerState = pagerState,
            itemsCount = itemsCount,
            itemContent = itemContent,
        )

        Surface(
            modifier =
                Modifier
                    .padding(small100)
                    .align(Alignment.BottomCenter),
            shape = CircleShape,
        ) {
            IndicatorDots(
                totalDots = itemsCount,
                selectedIndex = if (isDragged) pagerState.currentPage else pagerState.targetPage,
                dotSize = small100,
            )
        }
    }
}
