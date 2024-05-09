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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.diploma.work.design.composables.MainButton
import com.diploma.work.design.theme.large100
import com.diploma.work.design.theme.normal100
import com.diploma.work.design.theme.pagerIndicatorSize
import com.diploma.work.design.theme.small100
import com.diploma.work.design.theme.small150
import com.diploma.work.introduction.componants.IntroductionContent
import com.diploma.work.introduction.componants.IntroductionNextButtonText
import com.diploma.work.introduction.componants.PagerButtons
import com.diploma.work.introduction.componants.PagerIndicators
import com.diploma.work.navigation.ScreenRoute
import com.diploma.work.navigation.ScreenRoute.HOME
import com.diploma.work.navigation.ScreenRoute.INTRODUCTION
import kotlinx.coroutines.launch

private const val PAGER_COUNT = 3

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroductionScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: IntroductionViewModel = hiltViewModel(),
) {
    val pagerState = rememberPagerState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(normal100),
    ) {
        Column {
            Spacer(modifier = Modifier.height(large100))
            HorizontalPager(
                pageCount = PAGER_COUNT,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                state = pagerState,
                contentPadding = PaddingValues(small100),
                pageSpacing = small100,
            ) { page ->
                IntroductionContent(page = page)
            }
            Spacer(modifier = Modifier.height(normal100))
            PagerIndicators(pagerState = pagerState, pageCount = PAGER_COUNT)
            PagerButtons(
                pagerState = pagerState,
                pageCount = PAGER_COUNT,
                navController = navController,
                onClick = {
                    viewModel.setIntroductionShown()
                    navController.navigate(HOME) {
                        popUpTo(INTRODUCTION) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}



