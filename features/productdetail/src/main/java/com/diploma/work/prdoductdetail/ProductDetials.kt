package com.diploma.work.prdoductdetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.diploma.work.booking.Booking
import com.diploma.work.prdoductdetail.composables.ProductDetailsImages
import com.diploma.work.prdoductdetail.composables.ProductSize
import com.diploma.work.prdoductdetail.composables.ProductTitleAndSale
import com.diploma.work.common.componants.TopProductsLazyRow
import com.diploma.work.design.composables.MainButton
import com.diploma.work.design.theme.normal100
import com.diploma.work.design.theme.normal150
import com.diploma.work.prdoductdetail.composables.PriceAndBooking
import com.google.accompanist.pager.ExperimentalPagerApi

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductDetails(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.isBookingClicked) {
        Booking(
            onCloseBooking = viewModel::finishBookingLogic,
            onSaveBookedProduct = viewModel::saveBookedProduct,
            onRebookClicked = viewModel::onRebookClicked,
            productId = uiState.productDetail?.id ?: 0,
            bookedProductDate = uiState.bookedProductDate ?: 0,
            showBottomSheet = uiState.showBookedBottomSheet,
            showDatePicker = uiState.showDataPicker,
        )
    }

    uiState.productDetail?.let { data ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
        ) {
            ProductDetailsImages(
                productImages = data.images ?: emptyList(),
                isProductSavedIntoFavorites = uiState.isProductInFavorites,
                saveProductIntoFavoritesClicked = {
                    if (it) {
                        viewModel.saveProductToFavorites(data)
                    } else {
                        data.id?.let { productId ->
                            viewModel.deleteFromFavoriteProducts(
                                productId
                            )
                        }
                    }
                })
            ProductTitleAndSale(
                data.title.orEmpty(),
                data.salePercentage ?: 0,
            )
            PriceAndBooking(
                isProductBooked = uiState.isProductBooked,
                productDetails = data,
                bookingClicked = viewModel::bookingClicked
            )
            Spacer(modifier = Modifier.height(normal150))
            ProductSize(data.sizes.orEmpty())
            Text(
                text = stringResource(
                    id = R.string.sales_period,
                    data.saleStartsDate.orEmpty(),
                    data.saleEndsDate.orEmpty(),
                ),
                modifier = Modifier.padding(horizontal = normal100, vertical = normal150),
            )
            Text(
                text = stringResource(
                    id = R.string.sale_on_address,
                    data.address.orEmpty(),
                ),
                modifier = Modifier.padding(horizontal = normal100),
            )
            MainButton(
                modifier = Modifier
                    .padding(normal100)
                    .fillMaxWidth(),
                onClick = { openGoogleMaps(context, data.address.orEmpty()) },
                content = {
                    Text(text = stringResource(id = R.string.show_in_the_map))
                },
            )
            TopProductsLazyRow(
                productList = uiState.topProductsList,
                navController = navController,
            )
        }
    }
}

private fun openGoogleMaps(content: Context, address: String) = address.let {
    val intentUri = Uri.parse("geo:0,0?q=${Uri.encode(address)}")
    val mapIntent = Intent(Intent.ACTION_VIEW, intentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    content.startActivity(mapIntent)
}
