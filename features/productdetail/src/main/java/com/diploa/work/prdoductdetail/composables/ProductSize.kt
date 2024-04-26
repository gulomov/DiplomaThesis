package com.diploa.work.prdoductdetail.composables

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
import com.diploma.work.design.theme.fontSize16
import com.diploma.work.design.theme.normal100
import com.diploma.work.design.theme.small100

@Composable
fun ProductSize(
    sizes: List<ProductSizes>,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier) {
        items(sizes) {
            ProductSize(size = it.size)
        }
    }
}

@Composable
fun ProductSize(
    size: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = size,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = fontSize16,
        modifier = modifier
            .padding(start = normal100)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(small100),
            )
            .padding(small100),
    )
}
