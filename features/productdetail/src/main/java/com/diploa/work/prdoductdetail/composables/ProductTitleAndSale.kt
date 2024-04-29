package com.diploa.work.prdoductdetail.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.diploma.work.design.theme.fontSize16
import com.diploma.work.design.theme.fontSize20
import com.diploma.work.design.theme.normal100
import com.diploma.work.design.theme.normal150
import com.diploma.work.design.theme.small100

@Composable
internal fun ProductTitleAndSale(
    productTitle: String,
    salePercentage: Int,
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = productTitle,
            modifier =
                Modifier
                    .padding(horizontal = normal100, vertical = normal150)
                    .weight(1f),
            fontWeight = FontWeight.Bold,
            fontSize = fontSize20,
        )
        Text(
            text = "$salePercentage%",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = fontSize16,
            modifier =
                Modifier
                    .padding(normal100)
                    .background(
                        color = MaterialTheme.colorScheme.error,
                        shape = RoundedCornerShape(small100),
                    )
                    .padding(horizontal = normal100, vertical = small100),
        )
    }
}
