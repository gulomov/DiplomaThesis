package com.diploma.work.booking

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.diploma.work.booking.companents.DataPicker
import timber.log.Timber

@Composable
fun BookingScreen(
    onDismissBottomSheet: () -> Unit,
    onConfirmBottomSheet: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BookingScreenViewModel = hiltViewModel()
) {
    DataPicker(
        modifier = modifier,
        onConfirmClicked = {
            Timber.d("Confirmation date: $it")
            onConfirmBottomSheet()
            viewModel.closeCalendar()
        },
        onDismissClicked = { onDismissBottomSheet() }
    )
}