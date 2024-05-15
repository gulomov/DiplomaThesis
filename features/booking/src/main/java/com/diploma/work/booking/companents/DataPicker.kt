package com.diploma.work.booking.companents

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.diploma.work.booking.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DataPicker(
    onConfirmClicked: (Long) -> Unit,
    onDismissClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val datePickerState = rememberDatePickerState(
        initialDisplayedMonthMillis = System.currentTimeMillis(),
        yearRange = 2024..2024,
    )

    DatePickerDialog(
        modifier = modifier,
        onDismissRequest = { onDismissClicked() },
        confirmButton = {
            TextButton(
                onClick = {
                    datePickerState.selectedDateMillis?.let {
                        onConfirmClicked(datePickerState.selectedDateMillis ?: 0)
                    }
                },
                enabled = datePickerState.selectedDateMillis?.let {
                    it >= System.currentTimeMillis()
                } == true

            ) {
                Text(text = stringResource(R.string.data_picker_confirm_button))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissClicked() }) {
                Text(text = stringResource(R.string.data_picker_dismiss_button))
            }
        }) {
        DatePicker(state = datePickerState)
    }
}