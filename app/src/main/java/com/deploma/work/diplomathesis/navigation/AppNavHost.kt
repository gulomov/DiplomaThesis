package com.deploma.work.diplomathesis.navigation

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
import com.deploma.work.features.ScreenRoute
import com.deploma.work.features.ScreenRoute.HOME
import com.deploma.work.home.HomeScreen
import com.deploma.work.home.navigation.homeScreen
import com.deploma.work.introduction.navigation.introductionScreen
import com.deploma.work.splash.navigation.splashScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
) {
    val bottomBarState = rememberSaveable { (mutableStateOf(false)) }
    val topBarVisibility = rememberSaveable { (mutableStateOf(false)) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val coroutineScope = rememberCoroutineScope()

    when (navBackStackEntry?.destination?.route) {
        HOME -> {}
    }
    Scaffold(topBar = {}, bottomBar = {}, content = {
        NavHost(
            navController = navController,
            startDestination = ScreenRoute.INTRO_SPLASH,
            modifier = Modifier.padding(it),
        ) {
            splashScreen(navController)
            introductionScreen(navController)
            homeScreen(navController)
        }
    })
}
