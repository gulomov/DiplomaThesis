package com.diploma.work.common.componants

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.diploma.work.design.theme.recommendationImageHeightSize
import com.diploma.work.design.theme.recommendationImageWidthSize

@Composable
fun BrandImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(recommendationImageWidthSize)
            .height(recommendationImageHeightSize),
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(recommendationImageHeightSize)
                .align(Alignment.Center),
        )
    }
}