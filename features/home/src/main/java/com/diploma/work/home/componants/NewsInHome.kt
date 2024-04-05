package com.diploma.work.home.componants

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.diploma.work.design.composables.AutoSlidingCarousel
import com.diploma.work.design.theme.newsCarouselImageSize
import com.diploma.work.design.theme.normal100
import com.diploma.work.design.theme.small100
import com.diploma.work.repository.data.NewsInfo
import com.diploma.work.repository.data.NewsItem
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NewsInHome(
    news: NewsInfo,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface),
    ) {
        Spacer(modifier = Modifier.width(normal100))
        Text(
            text = news.title.orEmpty(),
            modifier = Modifier.padding(normal100),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(small100))
        Card(
            modifier = Modifier
                .padding(normal100)
                .fillMaxWidth(),
            shape = RoundedCornerShape(normal100)
        ) {
            news.newsList?.let { newsInfo ->
                if (newsInfo.isNotEmpty()) AutoSlidingCarousel(
                    itemsCount = newsInfo.size,
                    itemContent = {
                        NewsItem(news = newsInfo, index = it)
                    }
                )
            }
        }
    }
}

@Composable
fun NewsItem(
    index: Int,
    news: List<NewsItem>,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(news[index].image)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(newsCarouselImageSize)
                .align(Alignment.Center)
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(normal100)
        ) {
            news[index].body?.let {
                Text(
                    text = it,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            Spacer(modifier = Modifier.width(small100))
            news[index].title?.let {
                Text(
                    text = it,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            Spacer(modifier = Modifier.width(small100))
        }
    }
}