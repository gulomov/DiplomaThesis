package com.diploma.work.design.composables

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.diploma.work.design.theme.productsCarouselImageSize


@Composable
fun ProductHorizontalPager(imageUrl: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model =
        ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier.height(productsCarouselImageSize)
    )
}
