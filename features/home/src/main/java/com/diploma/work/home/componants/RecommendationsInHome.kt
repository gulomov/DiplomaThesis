package com.diploma.work.home.componants

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.diploma.work.common.componants.BrandImage
import com.diploma.work.design.theme.DiplomaThesisTheme
import com.diploma.work.design.theme.normal100
import com.diploma.work.design.theme.recommendationImageHeightSize
import com.diploma.work.design.theme.recommendationImageWidthSize
import com.diploma.work.design.theme.small100
import com.diploma.work.design.theme.small150
import com.diploma.work.home.R
import com.diploma.work.navigation.ScreenRoute
import com.diploma.work.repository.data.RecommendationItem
import timber.log.Timber

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun RecommendationsInHome(
    recommendations: List<RecommendationItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(pageCount = { recommendations.size })
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(small100),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = stringResource(id = R.string.homeScreenRecommendationsTitle),
            modifier = Modifier.padding(start = normal100, top = normal100),
            fontWeight = FontWeight.Bold,
        )
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = normal100, vertical = small100),
            pageSpacing = small150,
            pageSize = PageSize.Fixed(recommendationImageWidthSize),
        ) { page ->
            Card(
                modifier = Modifier
                    .width(recommendationImageWidthSize),
                shape = RoundedCornerShape(normal100),
                onClick = {
                    val route = ScreenRoute.RECOMMENDATION_DETAILS.replace(
                        "{brandName}", recommendations[page].brand.toString()
                    )
                    navController.navigate(route)
                }
            ) {
                BrandImage(
                    imageUrl = recommendations[page].image.toString(),
                    modifier
                        .width(recommendationImageWidthSize)
                        .height(recommendationImageHeightSize),
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun RecommendationsInHomePreview() {
    val listOfRecommendation = listOf(
        RecommendationItem(id = 1, "Brand", "Name"),
        RecommendationItem(id = 1, "Brand", "Name"),
        RecommendationItem(id = 1, "Brand", "Name"),
        RecommendationItem(id = 1, "Brand", "Name")
    )
    DiplomaThesisTheme {
        RecommendationsInHome(listOfRecommendation, rememberNavController())
    }
}
