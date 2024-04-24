package com.diploma.work.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.diploma.work.design.theme.normal100
import com.diploma.work.home.componants.GRIT_CELLS
import com.diploma.work.home.componants.NewsInHome
import com.diploma.work.home.componants.RecommendationsInHome
import com.diploma.work.home.componants.TopProductTitle
import com.diploma.work.home.componants.TopProductsInHome
import com.diploma.work.navigation.ScreenRoute.PRODUCTION_DETAIL
import okhttp3.internal.toImmutableList

private const val GRID_ITEM_SPAN = 3

@OptIn(ExperimentalMaterial3Api::class)
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

    LazyVerticalGrid(
        columns = GridCells.Fixed(GRIT_CELLS),
        modifier = modifier,
    ) {
        item(span = { GridItemSpan(GRID_ITEM_SPAN) }) {
            NewsInHome(news = news.toImmutableList())
            Spacer(modifier = Modifier.height(normal100))
        }
        item(span = { GridItemSpan(GRID_ITEM_SPAN) }) {
            RecommendationsInHome(recommendations = recommendations)
            Spacer(modifier = Modifier.height(normal100))
        }
        item(span = { GridItemSpan(GRID_ITEM_SPAN) }) {
            TopProductTitle()
        }
        items(topProducts) {
            TopProductsInHome(
                topProductsItem = it,
                productOnClick = { productId ->
                    val route = PRODUCTION_DETAIL.replace("{productId}", productId)
                    navController.navigate(route)
                },
            )
        }
    }
}
