package com.diploma.work.common.componants

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import com.diploma.work.common.R
import com.diploma.work.design.theme.fontSize16
import com.diploma.work.design.theme.fontSize18

@Composable
fun Prices(
    originalPrice: String,
    priceOnSale: String,
    modifier: Modifier = Modifier,
    textColor: Color? = null
) {
    Row(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.product_price, originalPrice),
            style = TextStyle(
                color = textColor ?: Color.Gray,
                fontSize = fontSize16,
                textDecoration = TextDecoration.LineThrough,
            ),
        )
        Text(
            text = stringResource(id = R.string.product_price, priceOnSale),
            style = TextStyle(
                color = MaterialTheme.colorScheme.error,
                fontSize = fontSize18,
            ),
        )
    }
}
