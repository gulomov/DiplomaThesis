package com.diploa.work.prdoductdetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.diploa.work.prdoductdetail.composables.ProductSize
import com.diploma.work.database.entity.ProductImages
import com.diploma.work.design.composables.IndicatorDots
import com.diploma.work.design.composables.MainButton
import com.diploma.work.design.composables.MainHorizontalPager
import com.diploma.work.design.theme.fontSize16
import com.diploma.work.design.theme.fontSize18
import com.diploma.work.design.theme.fontSize20
import com.diploma.work.design.theme.newsCarouselImageSize
import com.diploma.work.design.theme.normal100
import com.diploma.work.design.theme.normal150
import com.diploma.work.design.theme.productsCarouselImageSize
import com.diploma.work.design.theme.small100
import com.diploma.work.design.theme.small50
import com.diploma.work.prdoductdetail.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductDetails(
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = hiltViewModel(),
) {
    val productDetails by viewModel.productDetails.collectAsState()
    val context = LocalContext.current

    productDetails.images?.let { data ->
        Column(modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())) {
            ProductDetailsImages(productImages = data)
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = productDetails.title.orEmpty(),
                    modifier = Modifier
                        .padding(horizontal = normal100, vertical = normal150)
                        .weight(1f),
                    fontWeight = FontWeight.Bold,
                    fontSize = fontSize20,
                )
                Text(
                    text = "${productDetails.salePercentage}%",
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
            Row(modifier = Modifier.padding(start = normal100)) {
                Text(
                    text = productDetails.originalPrice.toString(),
                    modifier = Modifier.padding(small50),
                    style =
                    TextStyle(
                        color = Color.Gray,
                        fontSize = fontSize16,
                        textDecoration = TextDecoration.LineThrough,
                    ),
                )
                Text(
                    text = productDetails.priceOnSale.toString(),
                    style =
                    TextStyle(
                        color = MaterialTheme.colorScheme.error,
                        fontSize = fontSize18,
                    ),
                )
            }
            Spacer(modifier = Modifier.height(normal150))
            ProductSize(productDetails.sizes.orEmpty())
            Text(
                text =
                stringResource(
                    id = R.string.sales_period,
                    productDetails.saleStartsDate.orEmpty(),
                    productDetails.saleEndsDate.orEmpty(),
                ),
                modifier =
                Modifier
                    .padding(horizontal = normal100, vertical = normal150),
            )
            Text(
                text =
                stringResource(
                    id = R.string.sale_on_address,
                    productDetails.address.orEmpty(),
                ),
                modifier = Modifier.padding(horizontal = normal100),
            )
            MainButton(
                modifier =
                Modifier
                    .padding(normal100)
                    .fillMaxWidth(),
                onClick = { openGoogleMaps(context, productDetails.address.orEmpty()) },
                content = {
                    Text(text = stringResource(id = R.string.show_in_the_map))
                },
            )
        }
    }
}

private fun openGoogleMaps(
    content: Context,
    address: String,
) = address.let {
    val intentUri = Uri.parse("geo:0,0?q=${Uri.encode(address)}")
    val mapIntent = Intent(Intent.ACTION_VIEW, intentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    content.startActivity(mapIntent)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductDetailsImages(
    productImages: List<ProductImages>,
    pagerState: PagerState = remember { PagerState() },
) {
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    Box(modifier = Modifier.fillMaxWidth()) {
        MainHorizontalPager(
            pagerState = pagerState,
            itemsCount = productImages.size,
            itemContent = {
                AsyncImage(
                    model =
                    ImageRequest.Builder(LocalContext.current)
                        .data(productImages[it].imageUrl)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(productsCarouselImageSize)
                        .align(Alignment.Center),
                )
            },
        )
        Surface(
            modifier =
            Modifier
                .padding(small100)
                .align(Alignment.BottomCenter),
            shape = CircleShape,
        ) {
            IndicatorDots(
                totalDots = productImages.size,
                selectedIndex = if (isDragged) pagerState.currentPage else pagerState.targetPage,
                dotSize = small100,
            )
        }
    }
}
