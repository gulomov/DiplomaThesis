package com.diploma.work.prdoductdetail.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.diploma.work.common.componants.Prices
import com.diploma.work.design.composables.MainButton
import com.diploma.work.design.theme.normal100
import com.diploma.work.prdoductdetail.R
import com.diploma.work.repository.data.ProductDetailsData

@Composable
internal fun PriceAndBooking(
    isProductBooked: Boolean,
    productDetails: ProductDetailsData,
    bookingClicked: () -> Unit
) {
    Row {
        Prices(
            originalPrice = productDetails.originalPrice.toString(),
            priceOnSale = productDetails.priceOnSale.toString(),
            modifier = Modifier
                .padding(start = normal100)
                .weight(1f)
                .align(Alignment.CenterVertically),
        )
        if (!isProductBooked) {
            R.string.want_book
        } else {
            R.string.product_is_booked
        }.let { textRes ->
            MainButton(
                onClick = bookingClicked,
                content = { Text(text = stringResource(textRes)) },
                modifier = Modifier
                    .padding(end = normal100)
                    .align(Alignment.CenterVertically),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer)
            )
        }
    }
}