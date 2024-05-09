package com.diploma.work.introduction.componants

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.diploma.work.design.composables.MainButton
import com.diploma.work.design.theme.small100
import com.diploma.work.introduction.IntroductionViewModel
import com.diploma.work.introduction.R
import com.diploma.work.navigation.ScreenRoute
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun PagerButtons(
    pagerState: PagerState,
    pageCount: Int,
    navController: NavController,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,

    ) {
    val coroutineScope = rememberCoroutineScope()

    Row(modifier = modifier.fillMaxWidth()) {
        if (pagerState.currentPage > 0) {
            MainButton(
                modifier =
                Modifier
                    .padding(small100)
                    .wrapContentWidth()
                    .weight(1f),
                onClick = {
                    coroutineScope.launch {
                        val previousPage = pagerState.currentPage - 1
                        if (previousPage < pageCount) {
                            pagerState.animateScrollToPage(previousPage)
                        }
                    }
                },
                content = {
                    Text(
                        text = stringResource(id = R.string.introduction_button_previous),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                },
            )
        }
        MainButton(
            modifier = Modifier
                .padding(small100)
                .wrapContentWidth()
                .weight(1f),
            onClick = {
                coroutineScope.launch {
                    val nextPage = pagerState.currentPage + 1
                    if (nextPage < pageCount) {
                        pagerState.animateScrollToPage(nextPage)
                    } else {
                        onClick.invoke()
                    }
                }
            },
            content = {
                IntroductionNextButtonText(pagerState)
            },
        )
    }
}