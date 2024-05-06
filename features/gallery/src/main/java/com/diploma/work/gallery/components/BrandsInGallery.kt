package com.diploma.work.gallery.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.diploma.work.common.componants.BrandImage
import com.diploma.work.design.theme.normal100
import com.diploma.work.design.theme.recommendationImageWidthSize
import com.diploma.work.design.theme.small100
import com.diploma.work.repository.data.BrandsItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BrandsInGallery(
    brandsList: List<BrandsItem>,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()

    HorizontalPager(
        modifier = modifier,
        pageCount = brandsList.size,
        state = pagerState,
        contentPadding = PaddingValues(horizontal = normal100, vertical = small100),
        pageSpacing = small100,
        pageSize = PageSize.Fixed(recommendationImageWidthSize),
    ) { page ->
        Card(
            modifier = Modifier
                .padding(horizontal = normal100, vertical = small100)
                .width(recommendationImageWidthSize),
            shape = RoundedCornerShape(normal100),
        ) {
            BrandImage(imageUrl = brandsList[page].imageUrl.toString())
        }
    }
}