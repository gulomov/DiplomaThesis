package com.diploma.work.common.componants

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.diploma.work.common.R

@Composable
fun EmptyStateImage(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.empty_state),
        contentDescription = null,
        modifier = modifier
    )
}