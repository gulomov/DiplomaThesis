package com.diploma.work.common.componants

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.diploma.work.design.theme.small100
import com.diploma.work.design.theme.small50
import com.diploma.work.design.theme.topProductsImageHeightSize
import com.diploma.work.design.theme.topProductsImageWidthSize

@Composable
fun ProductImage(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(topProductsImageWidthSize)
            .height(topProductsImageHeightSize)
            .padding(small50)
            .clip(RoundedCornerShape(small100)),
    )
}