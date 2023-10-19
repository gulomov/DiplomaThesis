package com.deploma.work.screens.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.deploma.work.screens.ScreenRoute
import com.deploma.work.screens.home.HomeScreen

fun NavGraphBuilder.mainGraph(navController: NavController) {
    composable(ScreenRoute.Home.route) {
        HomeScreen()
    }
}
