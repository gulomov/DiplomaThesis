package com.diploma.work.introduction.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.deploma.work.features.ScreenRoute
import com.diploma.work.introduction.IntroductionScreen

fun NavGraphBuilder.introductionScreen(navController: NavController) {
    composable(ScreenRoute.INTRODUCTION) {
        IntroductionScreen(navController = navController)
    }
}
