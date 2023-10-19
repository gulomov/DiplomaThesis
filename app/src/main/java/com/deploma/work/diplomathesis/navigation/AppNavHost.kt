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
import com.deploma.work.screens.ScreenRoute
import com.deploma.work.screens.graph.IntroNav
import com.deploma.work.screens.graph.introGraph
import com.deploma.work.screens.graph.mainGraph

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
) {
    val bottomBarState = rememberSaveable { (mutableStateOf(false)) }
    val topBarVisibility = rememberSaveable { (mutableStateOf(false)) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val coroutineScope = rememberCoroutineScope()

    when (navBackStackEntry?.destination?.route) {
        ScreenRoute.Splash.route -> {}
    }
    Scaffold(topBar = {}, bottomBar = {}, content = {
        NavHost(
            navController = navController,
            startDestination = IntroNav.INTRO_ROUTE,
            modifier = Modifier.padding(it),
        ) {
            introGraph(navController)
            mainGraph(navController)
        }
    })
}
