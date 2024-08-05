package com.diploma.work.diplomathesis.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.diploma.work.prdoductdetail.ProductDetails
import com.diploma.work.favorites.FavoritesScreen
import com.diploma.work.gallery.GalleryScreen
import com.diploma.work.home.HomeScreen
import com.diploma.work.home.news.NewsDetailScreen
import com.diploma.work.home.recommendations.RecommendationsDetail
import com.diploma.work.navigation.ScreenRoute.FAVORITE
import com.diploma.work.navigation.ScreenRoute.GALLERY
import com.diploma.work.navigation.ScreenRoute.HOME
import com.diploma.work.navigation.ScreenRoute.NEWS_DETAILS
import com.diploma.work.navigation.ScreenRoute.PRODUCTION_DETAIL
import com.diploma.work.navigation.ScreenRoute.RECOMMENDATION_DETAILS

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.mainGraph(navController: NavController) {
    composable(HOME) {
        HomeScreen(navController = navController)
    }
    composable(
        route = PRODUCTION_DETAIL,
        arguments = listOf(
            navArgument("productId") {
                type = NavType.StringType
            },
        ),
    ) {
        ProductDetails(navController = navController)
    }
    composable(
        route = NEWS_DETAILS,
        arguments = listOf(
            navArgument("newsId") {
                type = NavType.IntType
            },
        ),
    ) {
        NewsDetailScreen(navController = navController)
    }
    composable(
        route = RECOMMENDATION_DETAILS,
        arguments = listOf(
            navArgument("brandName") {
                type = NavType.StringType
            }
        )
    ) {
        RecommendationsDetail(navController = navController)
    }
    composable(FAVORITE) {
        FavoritesScreen(navController = navController)
    }

    composable(
        route = GALLERY,
        arguments = listOf(
            navArgument("brand") {
                type = NavType.StringType
                nullable = true
                defaultValue = "all"
            },
        ),
    ) {
        GalleryScreen(navController = navController)
    }
}
