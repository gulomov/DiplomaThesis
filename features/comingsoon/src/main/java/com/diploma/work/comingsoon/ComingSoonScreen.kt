package com.diploma.work.comingsoon

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark

@Composable
fun ComingSoonScreen(
    modifier: Modifier = Modifier
) {
    Text(text = "Soon")
}

@PreviewLightDark
@Composable
private fun ComingSoonPreview() {
    MaterialTheme {
        ComingSoonScreen()
    }
}