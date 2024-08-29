package com.diploma.work.diplomathesis.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.diploma.work.diplomathesis.R
import com.diploma.work.navigation.ScreenRoute.COMING_SOON
import com.diploma.work.navigation.ScreenRoute.FAVORITE
import com.diploma.work.navigation.ScreenRoute.GALLERY
import com.diploma.work.navigation.ScreenRoute.HOME

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    @StringRes val title: Int,
) {
    data object Home : BottomNavItem(
        route = HOME,
        icon = Icons.Default.Home,
        title = R.string.bottom_nav_home
    )

    data object Favorite : BottomNavItem(
        route = FAVORITE,
        icon = Icons.Default.Favorite, // TODO: Will be changed favorite
        title = R.string.bottom_nav_favorite
    )

    data object Gallery : BottomNavItem(
        route = GALLERY,
        icon = Icons.Default.ShoppingCart,
        title = R.string.bottom_nav_gallery,
    )

    data object ComingSoon : BottomNavItem(
        route = COMING_SOON,
        icon = Icons.Default.DateRange,
        title = R.string.bottom_nav_coming_soon
    )

    companion object {
        val sections = listOf(Home, Favorite, Gallery, ComingSoon)
    }
}