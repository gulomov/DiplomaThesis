package com.diploma.work.comingsoon

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import timber.log.Timber

@Composable
fun ComingSoonScreen(
    modifier: Modifier = Modifier,
    viewModel: ComingSoonViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    Timber.d("What is ui state: $uiState")
}

@PreviewLightDark
@Composable
private fun ComingSoonPreview() {
    MaterialTheme {
        ComingSoonScreen()
    }
}