package com.diploma.work.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.diploma.work.design.composables.AutoSlidingCarousel
import com.diploma.work.design.theme.newsCarouselImageSize
import com.diploma.work.design.theme.normal100
import com.diploma.work.design.theme.small100
import com.diploma.work.repository.data.NewsInfo
import com.diploma.work.repository.data.NewsItem
import com.google.accompanist.pager.ExperimentalPagerApi

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val mutableNews by viewModel.news.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getNews()
    }
    NewsInHome(news = mutableNews)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NewsInHome(
    news: NewsInfo,
    modifier: Modifier = Modifier
) {
    val newsSize = news.newsList?.size ?: 0

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier.fillMaxWidth()
    ) {
        Card(
            modifier = Modifier.padding(normal100),
            shape = RoundedCornerShape(normal100)
        ) {
            news.newsList?.let { newsInfo ->
                AutoSlidingCarousel(
                    itemsCount = newsSize,
                    itemContent = {
                    }
                )
            }
        }
    }
}

@Composable
fun NewsItem(
    news: NewsItem,
) {
    Box {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(news.imageUrl)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(newsCarouselImageSize)
                .align(Alignment.Center)
        )
        Column {
            news.bodyText?.let {
                Text(
                    text = it,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            Spacer(modifier = Modifier.width(small100))
            news.titleText?.let {
                Text(
                    text = it,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            Spacer(modifier = Modifier.width(small100))
        }
    }
}
