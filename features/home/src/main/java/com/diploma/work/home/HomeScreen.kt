package com.diploma.work.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.diploma.work.design.theme.normal100
import com.diploma.work.design.theme.small100
import com.diploma.work.home.componants.NewsInHome
import com.diploma.work.repository.data.RecommendationsList
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.diploma.work.design.theme.large100
import com.diploma.work.design.theme.recommendationImageHeightSize
import com.diploma.work.design.theme.recommendationImageWidthSize
import com.diploma.work.repository.data.RecommendationItem

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val news by viewModel.news.collectAsState()
    val recommendations by viewModel.recommendationsList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getNews()
        viewModel.getRecommendationsList()

    }
    Column {
        NewsInHome(news = news)
        Spacer(modifier = Modifier.height(normal100))
        RecommendationsHome(recommendations = recommendations)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecommendationsHome(
    recommendations: RecommendationsList,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState()
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
    ) {
        Text(
            text = recommendations.title.orEmpty(),
            modifier = Modifier.padding(start = normal100, top = normal100),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(small100))
        HorizontalPager(
            pageCount = recommendations.recommendationsList.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = large100, vertical = small100),
            pageSpacing = small100,
            pageSize = PageSize.Fixed(recommendationImageWidthSize)
        ) { page ->
            // FIXME: Change size
            Card(
                modifier = Modifier
                    .padding(normal100)
                    .width(recommendationImageWidthSize),
                shape = RoundedCornerShape(normal100)
            ) {
                Recommendation(item = recommendations.recommendationsList[page])
            }
        }
    }
}

@Composable
fun Recommendation(
    item: RecommendationItem,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(recommendationImageWidthSize)
            .height(recommendationImageHeightSize)
    ) {
        AsyncImage(
            model = item.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(recommendationImageHeightSize)
                .align(Alignment.Center)
        )
    }
}