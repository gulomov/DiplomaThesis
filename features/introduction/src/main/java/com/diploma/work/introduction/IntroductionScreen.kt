package com.diploma.work.introduction

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.diploma.work.features.ScreenRoute.HOME
import com.diploma.work.features.ScreenRoute.INTRODUCTION
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroductionScreen(
    navController: NavController,
    viewModel: IntroductionViewModel = hiltViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val pageCount = 3

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Column {
            Spacer(modifier = Modifier.height(32.dp))
            HorizontalPager(
                pageCount = pageCount,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                state = pagerState,
                contentPadding = PaddingValues(8.dp),
                pageSpacing = 8.dp,
            ) { page ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column {
                        val imageResources = when (page) {
                            0 -> R.drawable.ic_intro_one
                            1 -> R.drawable.ic_intro_two
                            2 -> R.drawable.ic_intro_three
                            else -> 0
                        }

                        val textTitle = when (page) {
                            0 -> R.string.introduction_welcome_title
                            1 -> R.string.introduction_sales_info_title
                            2 -> R.string.introduction_enjoy_your_time_title
                            else -> 0
                        }

                        val textBody = when (page) {
                            0 -> R.string.introduction_welcome_text
                            1 -> R.string.introduction_sales_info_text
                            2 -> R.string.introduction_enjoy_your_time_text
                            else -> 0
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        Image(
                            painter = painterResource(id = imageResources),
                            contentDescription = null,
                            modifier = Modifier.padding(start = 8.dp),
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(id = textTitle),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(id = textBody),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp),
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                repeat(3) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .background(color, CircleShape)
                            .size(10.dp),
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                if (pagerState.currentPage > 0) {
                    com.diploma.work.composables.MainButton(
                        modifier = Modifier
                            .padding(8.dp)
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
                com.diploma.work.composables.MainButton(
                    modifier = Modifier
                        .padding(8.dp)
                        .wrapContentWidth()
                        .weight(1f),
                    onClick = {
                        coroutineScope.launch {
                            val nextPage = pagerState.currentPage + 1
                            if (nextPage < pageCount) {
                                pagerState.animateScrollToPage(nextPage)
                            } else {
                                viewModel.setIntroductionShown()
                                navController.navigate(HOME) {
                                    popUpTo(INTRODUCTION) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                    },
                    content = {
                        IntroductionNextButtonText(pagerState)
                    },
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroductionNextButtonText(pagerState: PagerState) {
    Text(
        text = when (pagerState.currentPage) {
            pagerState.initialPage -> stringResource(
                id = R.string.introduction_button_start,
            )

            1 -> stringResource(id = R.string.introduction_button_next)
            else -> stringResource(id = R.string.introduction_button_finish)
        },
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
    )
}
