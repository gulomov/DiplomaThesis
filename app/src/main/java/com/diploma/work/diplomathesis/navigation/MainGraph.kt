package com.diploma.work.diplomathesis.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.diploma.work.home.HomeScreen
import com.diploma.work.navigation.ScreenRoute.HOME

fun NavGraphBuilder.mainGraph(navController: NavController) {
    composable(HOME) {
        HomeScreen(navController)
    }
}
