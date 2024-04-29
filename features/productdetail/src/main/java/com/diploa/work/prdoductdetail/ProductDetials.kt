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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.diploa.work.prdoductdetail.composables.Prices
import com.diploa.work.prdoductdetail.composables.ProductDetailsImages
import com.diploa.work.prdoductdetail.composables.ProductSize
import com.diploa.work.prdoductdetail.composables.ProductTitleAndSale
import com.diploma.work.common.componants.TopProductsLazyRow
import com.diploma.work.database.entity.ProductImages
import com.diploma.work.design.composables.IndicatorDots
import com.diploma.work.design.composables.MainButton
import com.diploma.work.design.composables.MainHorizontalPager
import com.diploma.work.design.theme.fontSize16
import com.diploma.work.design.theme.fontSize18
import com.diploma.work.design.theme.fontSize20
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
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val productDetails by viewModel.productDetails.collectAsState()
    val topProducts by viewModel.topProductsList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getTopProductsList()
    }

    productDetails.images?.let { data ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            ProductDetailsImages(productImages = data)
            ProductTitleAndSale(
                productDetails.title.orEmpty(),
                productDetails.salePercentage ?: 0
            )
            Prices(
                productDetails.originalPrice.toString(),
                productDetails.priceOnSale.toString()
            )
            Spacer(modifier = Modifier.height(normal150))
            ProductSize(productDetails.sizes.orEmpty())
            Text(
                text =
                stringResource(
                    id = R.string.sales_period,
                    productDetails.saleStartsDate.orEmpty(),
                    productDetails.saleEndsDate.orEmpty(),
                ),
                modifier = Modifier
                    .padding(horizontal = normal100, vertical = normal150),
            )
            Text(
                text = stringResource(
                    id = R.string.sale_on_address,
                    productDetails.address.orEmpty(),
                ),
                modifier = Modifier.padding(horizontal = normal100),
            )
            MainButton(
                modifier = Modifier
                    .padding(normal100)
                    .fillMaxWidth(),
                onClick = { openGoogleMaps(context, productDetails.address.orEmpty()) },
                content = {
                    Text(text = stringResource(id = R.string.show_in_the_map))
                },
            )
            TopProductsLazyRow(
                productList = topProducts,
                navController = navController
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
