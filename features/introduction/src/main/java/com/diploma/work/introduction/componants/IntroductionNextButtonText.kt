package com.diploma.work.introduction.componants

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.diploma.work.introduction.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun IntroductionNextButtonText(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
) {
    Text(
        text =
        when (pagerState.currentPage) {
            0 -> stringResource(id = R.string.introduction_button_start)
            1 -> stringResource(id = R.string.introduction_button_next)
            else -> stringResource(id = R.string.introduction_button_finish)
        },
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxWidth(),
    )
}