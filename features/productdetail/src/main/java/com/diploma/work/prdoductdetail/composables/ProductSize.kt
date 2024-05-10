package com.diploma.work.prdoductdetail.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.diploma.work.database.entity.ProductSizes
import com.diploma.work.design.composables.MainButton
import com.diploma.work.design.theme.fontSize16
import com.diploma.work.design.theme.small100
import com.diploma.work.design.theme.small150

@Composable
fun ProductSize(
    sizes: List<ProductSizes>,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier
            .padding(start = small150)
            .background(
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                shape = RoundedCornerShape(small100),
            )

    ) {
        items(sizes) {
            ProductSize(size = it.size)
        }
    }
}

@Composable
fun ProductSize(
    size: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = size,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = fontSize16,
        modifier = modifier.padding(small100),
    )
}
