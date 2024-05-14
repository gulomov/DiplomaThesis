package com.diploma.work.booking

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.diploma.work.booking.companents.DataPicker
import timber.log.Timber

@Composable
fun BookingScreen(
    onCloseBooking: () -> Unit,
    productId: Int,
    modifier: Modifier = Modifier,
    viewModel: BookingScreenViewModel = hiltViewModel()
) {
    DataPicker(
        modifier = modifier,
        onConfirmClicked = {
            onCloseBooking()
            viewModel.saveBookedProduct(productId, it)
        },
        onDismissClicked = { onCloseBooking() }
    )
}