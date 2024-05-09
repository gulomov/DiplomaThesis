package com.diploma.work.home.componants

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.diploma.work.design.composables.AutoSlidingCarousel
import com.diploma.work.design.theme.newsCarouselImageSize
import com.diploma.work.design.theme.normal100
import com.diploma.work.design.theme.small100
import com.diploma.work.home.R
import com.diploma.work.navigation.ScreenRoute
import com.diploma.work.repository.data.NewsItem
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun NewsInHome(
    news: List<NewsItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier =
        modifier
            .fillMaxWidth(),
    ) {
        Spacer(modifier = Modifier.width(normal100))
        Text(
            text = stringResource(id = R.string.homeScreenNewsTitle),
            modifier = Modifier.padding(normal100),
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.width(small100))
        Card(
            modifier =
            Modifier
                .padding(normal100)
                .fillMaxWidth(),
            shape = RoundedCornerShape(normal100),
        ) {
            news.let { newsInfo ->
                if (newsInfo.isNotEmpty()) {
                    AutoSlidingCarousel(
                        itemsCount = newsInfo.size,
                        itemContent = {
                            NewsItem(news = newsInfo, index = it, newsItemClicked = { newsId ->
                                val route = ScreenRoute.NEWS_DETAILS.replace(
                                    "{newsId}", newsId.toString()
                                )
                                navController.navigate(route)
                            })
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun NewsItem(
    index: Int,
    news: List<NewsItem>,
    newsItemClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.clickable(onClick = {
        newsItemClicked.invoke(news[index].id ?: 0)
    })) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(news[index].image)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier =
            Modifier
                .height(newsCarouselImageSize)
                .align(Alignment.Center),
        )
        Column(
            modifier =
            Modifier
                .align(Alignment.BottomStart)
                .padding(normal100),
        ) {
            news[index].body?.let {
                Text(
                    text = it,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )
            }
            Spacer(modifier = Modifier.width(small100))
            news[index].title?.let {
                Text(
                    text = it,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )
            }
            Spacer(modifier = Modifier.width(small100))
        }
    }
}
