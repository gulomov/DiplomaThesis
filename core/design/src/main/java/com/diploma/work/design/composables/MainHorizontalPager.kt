package com.diploma.work.design.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.diploma.work.design.theme.small100
import com.diploma.work.design.theme.small50
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainHorizontalPager(
    itemsCount: Int,
    itemContent: @Composable (index: Int) -> Unit,
    pagerState: PagerState,
) {
    com.google.accompanist.pager.HorizontalPager(
        count = itemsCount,
        state = pagerState,
        itemSpacing = small100,
    ) { page ->
        itemContent(page)
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
        modifier =
            modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(small50),
    ) {
        items(totalDots) {
            IndicatorDot(
                size = dotSize,
                color = if (it == selectedIndex) selectedColor else unSelected,
            )
        }
    }
}

@Composable
fun IndicatorDot(
    size: Dp,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .size(size)
                .clip(CircleShape)
                .background(color),
    )
}
