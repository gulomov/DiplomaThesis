package com.deploma.work.screens.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.deploma.work.screens.splash.SplashScreen
import com.deploma.work.screens.graph.IntroNav.INTRO_ROUTE
import com.deploma.work.screens.graph.IntroNav.INTRO_SPLASH

fun NavGraphBuilder.introGraph(navController: NavController) {
    navigation(startDestination = INTRO_SPLASH, route = INTRO_ROUTE) {
        composable(INTRO_SPLASH) {
            SplashScreen(navController)
        }
    }
}

object IntroNav {
    const val INTRO_ROUTE = "intro"
    const val INTRO_SPLASH = "splash"
}
