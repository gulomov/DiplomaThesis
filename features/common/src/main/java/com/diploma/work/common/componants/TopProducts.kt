package com.diploma.work.common.componants

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.diploma.work.common.R
import com.diploma.work.design.theme.normal100
import com.diploma.work.design.theme.small100
import com.diploma.work.design.theme.small150
import com.diploma.work.design.theme.small50
import com.diploma.work.design.theme.topProductsImageHeightSize
import com.diploma.work.design.theme.topProductsImageWidthSize
import com.diploma.work.navigation.ScreenRoute.PRODUCTION_DETAIL
import com.diploma.work.repository.data.TopProductItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopProductsLazyRow(
    productList: List<TopProductItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.topProductsTitle),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = normal100)
        )
        LazyRow(
            contentPadding = PaddingValues(
                horizontal = normal100,
                vertical = small150
            ),
            horizontalArrangement = Arrangement.spacedBy(small150)
        ) {
            items(productList) {
                TopProducts(
                    topProductsItem = it,
                    productOnClick = { productId ->
                        val route = PRODUCTION_DETAIL.replace("{productId}", productId)
                        navController.navigate(route)
                    },
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun TopProducts(
    topProductsItem: TopProductItem,
    productOnClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        onClick = { topProductsItem.id?.let { productOnClick.invoke(it.toString()) } },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceTint),
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(small150),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Box {
                AsyncImage(
                    model = topProductsItem.images?.first()?.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(topProductsImageWidthSize)
                        .height(topProductsImageHeightSize)
                        .padding(small50)
                        .clip(RoundedCornerShape(small100)),
                )
                Text(
                    text = stringResource(
                        id = R.string.productsSalePercentage,
                        topProductsItem.salePercentage.toString()
                    ),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = small50, end = small50)
                        .background(
                            color = MaterialTheme.colorScheme.error,
                            shape = RoundedCornerShape(topEnd = small100),
                        )
                        .padding(small50),
                )
            }
            Text(
                text = topProductsItem.title.orEmpty(),
                modifier = Modifier.padding(start = normal100),
                textAlign = TextAlign.Center,
            )
            Text(
                text = "${topProductsItem.saleStartsDate} - ${topProductsItem.saleEndsDate}",
                modifier = Modifier.padding(start = normal100, bottom = small100),
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun TopProductsLazyRowPreview() {
    val productList = listOf(
        TopProductItem(),
        TopProductItem(),
        TopProductItem(),
    )
    TopProductsLazyRow(productList = productList, navController = rememberNavController())

}