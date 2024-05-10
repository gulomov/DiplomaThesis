package com.diploma.work.prdoductdetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.diploma.work.booking.BookingScreen
import com.diploma.work.common.componants.Prices
import com.diploma.work.prdoductdetail.composables.ProductDetailsImages
import com.diploma.work.prdoductdetail.composables.ProductSize
import com.diploma.work.prdoductdetail.composables.ProductTitleAndSale
import com.diploma.work.common.componants.TopProductsLazyRow
import com.diploma.work.design.composables.MainButton
import com.diploma.work.design.theme.normal100
import com.diploma.work.design.theme.normal150
import com.diploma.work.prdoductdetail.composables.PriceAndBooking
import com.diploma.work.repository.data.ProductDetailsData
import com.google.accompanist.pager.ExperimentalPagerApi
import timber.log.Timber

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProductDetails(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val productDetails by viewModel.productDetails.collectAsState()
    val topProducts by viewModel.topProductsList.collectAsState()
    val isProductSavedIntoFavorites by viewModel.isProductInFavorites.collectAsState()
    var showBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getTopProductsList()
    }

    if (showBottomSheet) {
        BookingScreen() {
            showBottomSheet = false
        }
    }

    productDetails.images?.let { data ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
        ) {
            ProductDetailsImages(
                productImages = data,
                isProductSavedIntoFavorites = isProductSavedIntoFavorites,
                saveProductIntoFavoritesClicked = {
                    if (it) {
                        viewModel.saveProductToFavorites(productDetails)
                    } else {
                        productDetails.id?.let { productId ->
                            viewModel.deleteFromFavoriteProducts(
                                productId
                            )
                        }
                    }
                })
            ProductTitleAndSale(
                productDetails.title.orEmpty(),
                productDetails.salePercentage ?: 0,
            )
            PriceAndBooking(productDetails, bookingClicked = {
                showBottomSheet = true
            })
            Spacer(modifier = Modifier.height(normal150))
            ProductSize(productDetails.sizes.orEmpty())
            Text(
                text = stringResource(
                    id = R.string.sales_period,
                    productDetails.saleStartsDate.orEmpty(),
                    productDetails.saleEndsDate.orEmpty(),
                ),
                modifier =
                Modifier
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
                navController = navController,
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
