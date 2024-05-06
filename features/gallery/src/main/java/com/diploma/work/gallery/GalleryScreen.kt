package com.diploma.work.gallery

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.diploma.work.common.componants.BrandImage
import com.diploma.work.design.theme.normal100
import com.diploma.work.design.theme.recommendationImageWidthSize
import com.diploma.work.design.theme.small100
import com.diploma.work.design.theme.small50
import com.diploma.work.repository.data.BrandsItem

@Composable
fun GalleryScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: GalleryScreenViewModel = hiltViewModel()
) {
    val brands by viewModel.brandsList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getBrands()
    }
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier,
    ) {
        BrandsInGallery(brands)
    }
}

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