package com.deploma.work.screens

sealed class ScreenRoute(val route: String) {
    object Splash : ScreenRoute("splash")
    object Introduction : ScreenRoute("introduction")
    object Home : ScreenRoute("main")
}
