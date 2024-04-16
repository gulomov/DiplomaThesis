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
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.diploma.work.design.theme.large100
import com.diploma.work.design.theme.recommendationImageHeightSize
import com.diploma.work.design.theme.recommendationImageWidthSize
import com.diploma.work.home.componants.RecommendationsHome
import com.diploma.work.repository.data.RecommendationItem
import okhttp3.internal.toImmutableList

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
        NewsInHome(news = news.toImmutableList())
        Spacer(modifier = Modifier.height(normal100))
        RecommendationsHome(recommendations = recommendations)
    }
}