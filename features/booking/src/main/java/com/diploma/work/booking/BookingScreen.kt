package com.diploma.work.booking

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.diploma.work.booking.companents.BottomSheetConfirmation
import com.diploma.work.booking.companents.DataPicker
import kotlinx.coroutines.flow.MutableStateFlow

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookingScreen(
    onCloseBooking: () -> Unit,
    productId: Int,
    showDatePicker: Boolean,
    showBottomSheet: Boolean,
    modifier: Modifier = Modifier,
    viewModel: BookingScreenViewModel = hiltViewModel()
) {
    viewModel.getBookedProductDetail(productId)

    val showDatePickerStateFlow = remember { MutableStateFlow(showDatePicker) }
    val showBottomSheetStateFlow = remember { MutableStateFlow(showBottomSheet) }

    val showDatePickerState by showDatePickerStateFlow.collectAsState()
    val showBottomSheetState by showBottomSheetStateFlow.collectAsState()
    val bookedProductDate by viewModel.bookedProductDate.collectAsState()

    if (showBottomSheetState) {
        BottomSheetConfirmation(
            bookedProductDate = bookedProductDate,
            onDismissBottomSheet = {
                onCloseBooking()
            },
            onRebookClicked = {
                showDatePickerStateFlow.value = true
                showBottomSheetStateFlow.value = false
            }
        )
    }

    if (showDatePickerState) {
        DataPicker(
            modifier = modifier,
            onConfirmClicked = {
                onCloseBooking()
                viewModel.saveBookedProduct(productId, it)
            },
            onDismissClicked = { onCloseBooking() }
        )
    }
}