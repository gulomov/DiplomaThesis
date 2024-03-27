package com.diploma.work.splash.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.diploma.work.features.ScreenRoute.INTRO_SPLASH
import com.diploma.work.splash.SplashScreen

fun NavGraphBuilder.splashScreen(navController: NavController) {
    composable(INTRO_SPLASH) {
        SplashScreen(navController)
    }
}
