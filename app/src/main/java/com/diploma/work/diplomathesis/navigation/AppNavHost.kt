package com.diploma.work.diplomathesis.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.diploma.work.design.composables.ThesisTopBar
import com.diploma.work.introduction.navigation.introductionScreen
import com.diploma.work.navigation.ScreenRoute.FAVORITE
import com.diploma.work.navigation.ScreenRoute.GALLERY
import com.diploma.work.navigation.ScreenRoute.HOME
import com.diploma.work.navigation.ScreenRoute.INTRODUCTION
import com.diploma.work.navigation.ScreenRoute.INTRO_SPLASH
import com.diploma.work.navigation.ScreenRoute.NEWS_DETAILS
import com.diploma.work.navigation.ScreenRoute.PRODUCTION_DETAIL
import com.diploma.work.navigation.ScreenRoute.RECOMMENDATION_DETAILS
import com.diploma.work.navigation.ScreenRoute.SEARCH
import com.diploma.work.splash.navigation.splashScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val bottomBarVisible = rememberSaveable { (mutableStateOf(true)) }
    val topBarVisibility = rememberSaveable { (mutableStateOf(true)) }
    val backArrowVisibility = rememberSaveable { (mutableStateOf(true)) }
    val searchIconVisibility = rememberSaveable { (mutableStateOf(true)) }


    when (navBackStackEntry?.destination?.route) {
        INTRO_SPLASH, INTRODUCTION -> {
            topBarVisibility.value = false
            bottomBarVisible.value = false
            searchIconVisibility.value = false
            backArrowVisibility.value = false
        }

        HOME -> {
            topBarVisibility.value = true
            bottomBarVisible.value = true
            searchIconVisibility.value = true
            backArrowVisibility.value = false
        }

        FAVORITE, GALLERY -> {
            backArrowVisibility.value = false
            searchIconVisibility.value = true
        }

        PRODUCTION_DETAIL, NEWS_DETAILS, RECOMMENDATION_DETAILS -> {
            backArrowVisibility.value = true
            searchIconVisibility.value = true
        }

        SEARCH -> {
            backArrowVisibility.value = true
            searchIconVisibility.value = false
        }

        else -> {
            topBarVisibility.value = true
            bottomBarVisible.value = true
        }
    }

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp),
        topBar = {
            if (topBarVisibility.value)
                ThesisTopBar(
                    backArrowVisibility = backArrowVisibility.value,
                    searchingVisibility = searchIconVisibility.value,
                    onSearchClicked = { navController.navigate(SEARCH) },
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
