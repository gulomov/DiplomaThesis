package com.diploma.work.introduction.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.diploma.work.introduction.IntroductionScreen
import com.diploma.work.navigation.ScreenRoute.INTRODUCTION

fun NavGraphBuilder.introductionScreen(navController: NavController) {
    composable(INTRODUCTION) {
        IntroductionScreen(navController = navController)
    }
}
