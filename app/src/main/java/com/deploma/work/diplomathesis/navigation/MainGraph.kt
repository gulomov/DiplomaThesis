package com.deploma.work.diplomathesis.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.deploma.work.features.ScreenRoute
import com.deploma.work.home.HomeScreen

fun NavGraphBuilder.mainGraph(navController: NavController) {
    composable(ScreenRoute.HOME) {
        HomeScreen(navController)
    }
}
