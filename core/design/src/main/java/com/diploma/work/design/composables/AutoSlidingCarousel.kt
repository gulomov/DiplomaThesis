package com.diploma.work.design.composables

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.diploma.work.design.theme.small100
import com.diploma.work.design.theme.small50
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
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
            animationSpec = tween(5000) // FIXME: This should be reconsidered
        )
    }

    Box(modifier = modifier.fillMaxWidth()) {
        HorizontalPager(
            count = itemsCount,
            state = pagerState,
            itemSpacing = small100
        ) { page ->
            itemContent(page)
        }

        Surface(
            modifier = Modifier
                .padding(small100)
                .align(Alignment.BottomCenter),
            shape = CircleShape,
        ) {
            IndicatorDots(
                totalDots = itemsCount,
                selectedIndex = if (isDragged) pagerState.currentPage else pagerState.targetPage,
                dotSize = small100
            )
        }
    }
}

@Composable
fun IndicatorDots(
    totalDots: Int,
    selectedIndex: Int,
    dotSize: Dp,
    modifier: Modifier = Modifier,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unSelected: Color = MaterialTheme.colorScheme.onPrimary,
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(small50)
    ) {
        items(totalDots) {
            IndicatorDot(
                size = dotSize,
                color = if (it == selectedIndex) selectedColor else unSelected
            )
        }
    }
}

@Composable
fun IndicatorDot(
    size: Dp,
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
    )
}