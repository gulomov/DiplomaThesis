package com.diploma.work.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.diploma.work.common.componants.ProgressCircle
import com.diploma.work.common.componants.TopProductsLazyRow
import com.diploma.work.design.theme.DiplomaThesisTheme
import com.diploma.work.design.theme.normal100
import com.diploma.work.design.theme.small150
import com.diploma.work.home.componants.NewsInHome
import com.diploma.work.home.componants.RecommendationsInHome
import okhttp3.internal.toImmutableList

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.loadingValue) {
        ProgressCircle()
    } else {
        Column(
            verticalArrangement = Arrangement.spacedBy(small150),
            modifier = modifier.verticalScroll(rememberScrollState()),
        ) {
            NewsInHome(news = uiState.newsInfo.toImmutableList(), navController = navController)
            RecommendationsInHome(
                recommendations = uiState.recommendationsList,
                navController = navController
            )
            TopProductsLazyRow(
                productList = uiState.topProductsList,
                navController = navController,
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun HomeScreenPreview() {
    DiplomaThesisTheme {
        HomeScreen(rememberNavController())
    }
}