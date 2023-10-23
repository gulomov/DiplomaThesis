package com.deploma.work.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.deploma.work.features.ScreenRoute
import com.deploma.work.home.HomeScreen

fun NavGraphBuilder.homeScreen(navController: NavController) {
    composable(ScreenRoute.HOME) {
        HomeScreen()
    }
}
