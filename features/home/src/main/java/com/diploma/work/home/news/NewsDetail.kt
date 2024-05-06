package com.diploma.work.home.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.diploma.work.design.theme.newsCarouselImageSize
import com.diploma.work.design.theme.normal150

@Composable
fun NewsDetailScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: NewsDetailViewModel = hiltViewModel()
) {
    val details by viewModel.details.collectAsState()

    Column(modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(details.image)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier =
            Modifier
                .height(newsCarouselImageSize)
                .align(Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.height(normal150))
        Text(
            text = details.title.toString(),
            modifier = Modifier.padding(horizontal = normal150),
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(normal150))
        Text(
            text = details.body.toString(),
            modifier = Modifier.padding(horizontal = normal150),
            fontWeight = FontWeight.Bold,
        )
    }
}