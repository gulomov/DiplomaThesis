package com.diploma.work.booking

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.diploma.work.booking.companents.BottomSheetConfirmation
import com.diploma.work.booking.companents.DataPicker
import com.diploma.work.repository.data.BookedProduct

@Composable
fun Booking(
    onCloseBooking: () -> Unit,
    onSaveBookedProduct: (BookedProduct) -> Unit,
    onRebookClicked: () -> Unit,
    productId: Int,
    showDatePicker: Boolean,
    showBottomSheet: Boolean,
    bookedProductDate: Long,
    modifier: Modifier = Modifier,
) {

    if (showBottomSheet) {
        BottomSheetConfirmation(
            bookedProductDate = bookedProductDate,
            onDismissBottomSheet = onCloseBooking,
            onRebookClicked = onRebookClicked
        )
    }

    if (showDatePicker) {
        DataPicker(
            modifier = modifier,
            onConfirmClicked = {
                onCloseBooking()
                onSaveBookedProduct(BookedProduct(productId, it))
            },
            onDismissClicked = onCloseBooking
        )
    }
}