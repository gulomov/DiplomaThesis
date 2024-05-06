package com.diploma.work.diplomathesis.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.diploma.work.diplomathesis.R
import com.diploma.work.navigation.ScreenRoute
import com.diploma.work.navigation.ScreenRoute.GALLERY

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    @StringRes val title: Int,
) {
    object Home : BottomNavItem(
        route = ScreenRoute.HOME,
        icon = Icons.Default.Home,
        title = R.string.bottom_nav_home
    )

    object Favorite : BottomNavItem(
        route = ScreenRoute.FAVORITE,
        icon = Icons.Default.Favorite, // TODO: Will be changed favorite
        title = R.string.bottom_nav_favorite
    )

    object Gallery : BottomNavItem(
        route = ScreenRoute.GALLERY,
        icon = Icons.Default.ShoppingCart,
        title = R.string.bottom_nav_gallery,
    )

    companion object {
        val sections = listOf(Home, Favorite, Gallery)
    }
}