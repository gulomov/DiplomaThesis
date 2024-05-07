package com.diploma.work.common.componants

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.diploma.work.common.R
import com.diploma.work.design.composables.IndicatorDots
import com.diploma.work.design.composables.MainHorizontalPager
import com.diploma.work.design.theme.small100
import com.diploma.work.design.theme.small50
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun GenericProductImages(
    productPercentage: String,
    isFavorite: Boolean,
    imageUrls: List<String>,
    onSaveClick: (Boolean) -> Unit,
) {
    val pagerState = rememberPagerState()
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    Box {
        MainHorizontalPager(
            pagerState = pagerState,
            itemsCount = imageUrls.size,
            itemContent = { index ->
                ProductImage(imageUrl = imageUrls[index])
            }
        )
        Text(
            text = stringResource(
                id = R.string.topProductsPercentage,
                productPercentage
            ),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = small50, end = small50)
                .background(
                    color = MaterialTheme.colorScheme.error,
                    shape = RoundedCornerShape(topEnd = small100)
                )
                .padding(small50),
        )
        Image(
            painter = if (isFavorite) {
                painterResource(R.drawable.ic_bookmark)
            } else {
                painterResource(R.drawable.ic_bookmark_border)
            },
            contentDescription = "Favorite",
            modifier = Modifier
                .padding(end = small50, bottom = small50)
                .clickable(onClick = { onSaveClick.invoke(false) })
                .align(Alignment.BottomEnd)
        )
        Surface(
            modifier = Modifier
                .padding(small100)
                .align(Alignment.BottomCenter),
            shape = CircleShape
        ) {
            IndicatorDots(
                totalDots = imageUrls.size,
                selectedIndex = if (isDragged) {
                    pagerState.currentPage
                } else pagerState.targetPage,
                dotSize = small100
            )
        }
    }
}
