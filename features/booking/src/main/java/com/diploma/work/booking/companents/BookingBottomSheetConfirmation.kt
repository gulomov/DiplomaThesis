package com.diploma.work.booking.companents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.diploma.work.booking.R
import com.diploma.work.design.composables.MainButton
import com.diploma.work.design.theme.normal100

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun BottomSheetConfirmation(
    onDismissBottomSheet: () -> Unit,
    modifier: Modifier = Modifier
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = onDismissBottomSheet,
        sheetState = modalBottomSheetState,
        modifier = modifier
    ) {
        Column(modifier = Modifier) {
            Image(
                painter = painterResource(id = R.drawable.ic_booking),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally)
            )
            Row {
                MainButton(
                    onClick = { },
                    content = { Text(text = stringResource(R.string.button_book_text)) },
                    modifier = modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                        .padding(normal100),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer)
                )
                MainButton(
                    onClick = { onDismissBottomSheet() },
                    content = { Text(text = stringResource(R.string.button_nope_text)) },
                    modifier = modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                        .padding(normal100),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                )
            }
            Spacer(modifier = Modifier.height(normal100))
        }
    }
}