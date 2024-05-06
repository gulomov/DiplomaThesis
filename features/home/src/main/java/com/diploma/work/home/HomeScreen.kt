package com.diploma.work.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.diploma.work.common.componants.TopProductsLazyRow
import com.diploma.work.design.theme.normal100
import com.diploma.work.home.componants.NewsInHome
import com.diploma.work.home.componants.RecommendationsInHome
import okhttp3.internal.toImmutableList

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val news by viewModel.news.collectAsState()
    val recommendations by viewModel.recommendationsList.collectAsState()
    val topProducts by viewModel.topProductsList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getNews()
        viewModel.getRecommendationsList()
        viewModel.getTopProductsList()
    }

    LazyColumn(
        modifier = modifier,
    ) {
        item {
            NewsInHome(news = news.toImmutableList(), navController = navController)
            Spacer(modifier = Modifier.height(normal100))
        }
        item {
            RecommendationsInHome(recommendations = recommendations)
            Spacer(modifier = Modifier.height(normal100))
        }
        item {
            TopProductsLazyRow(
                productList = topProducts,
                navController = navController,
            )
        }
    }
}
