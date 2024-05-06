package com.diploma.work.diplomathesis.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.diploma.work.design.composables.ThesisTopBar
import com.diploma.work.introduction.navigation.introductionScreen
import com.diploma.work.navigation.ScreenRoute.FAVORITE
import com.diploma.work.navigation.ScreenRoute.HOME
import com.diploma.work.navigation.ScreenRoute.INTRODUCTION
import com.diploma.work.navigation.ScreenRoute.INTRO_SPLASH
import com.diploma.work.navigation.ScreenRoute.NEWS_DETAILS
import com.diploma.work.navigation.ScreenRoute.PRODUCTION_DETAIL
import com.diploma.work.splash.navigation.splashScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    val bottomBarVisible = rememberSaveable { (mutableStateOf(true)) }
    val topBarVisibility = rememberSaveable { (mutableStateOf(true)) }
    val topBarBackArrowVisibility = rememberSaveable { (mutableStateOf(true)) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    when (navBackStackEntry?.destination?.route) {
        HOME -> {
            topBarVisibility.value = true
            topBarBackArrowVisibility.value = false
            bottomBarVisible.value = true
        }

        FAVORITE -> {
            topBarVisibility.value = true
            topBarBackArrowVisibility.value = false
            bottomBarVisible.value = true
        }

        INTRO_SPLASH -> {
            topBarVisibility.value = false
            topBarBackArrowVisibility.value = false
            bottomBarVisible.value = false
        }

        INTRODUCTION -> {
            topBarVisibility.value = false
            bottomBarVisible.value = false
        }

        PRODUCTION_DETAIL -> {
            topBarBackArrowVisibility.value = true
            bottomBarVisible.value = true
        }

        NEWS_DETAILS->{
            topBarBackArrowVisibility.value = true
            bottomBarVisible.value = true
        }

        else -> topBarVisibility.value = true
    }
    Scaffold(
        topBar = {
            if (topBarVisibility.value)
                ThesisTopBar(
                    backArrowVisibility = topBarBackArrowVisibility.value,
                    navController = navController,
                )
        },
        bottomBar = {
            if (bottomBarVisible.value)
                BottomNavigationBar(navController = navController)
        },
    ) {
        NavHost(
            navController = navController,
            startDestination = INTRO_SPLASH,
            modifier = Modifier.padding(it),
        ) {
            splashScreen(navController)
            introductionScreen(navController)
            mainGraph(navController)
        }
    }
}
