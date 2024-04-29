package com.diploa.work.prdoductdetail.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import com.diploma.work.design.theme.fontSize16
import com.diploma.work.design.theme.fontSize18
import com.diploma.work.design.theme.normal100
import com.diploma.work.design.theme.small50

@Composable
internal fun Prices(
    originalPrice: String,
    priceOnSale: String,
) {
    Row(modifier = Modifier.padding(start = normal100)) {
        Text(
            text = originalPrice,
            modifier = Modifier.padding(small50),
            style =
                TextStyle(
                    color = Color.Gray,
                    fontSize = fontSize16,
                    textDecoration = TextDecoration.LineThrough,
                ),
        )
        Text(
            text = priceOnSale,
            style =
                TextStyle(
                    color = MaterialTheme.colorScheme.error,
                    fontSize = fontSize18,
                ),
        )
    }
}
