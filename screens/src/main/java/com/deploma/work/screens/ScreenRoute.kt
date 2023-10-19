package com.deploma.work.screens

sealed class ScreenRoute(val route: String) {
    object Splash : ScreenRoute("splash")
    object Home : ScreenRoute("main")
}
