package com.diploma.work.diplomathesis.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = contentColorFor(MaterialTheme.colorScheme.outline),
        tonalElevation = 10.dp,
    ) {
        BottomNavItem.sections.forEach { sections ->
            NavigationBarItem(
                selected = currentRoute == sections.route,
                onClick = {
                    navController.navigate(sections.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true

                    }
                },
                icon = { Icon(imageVector = sections.icon, contentDescription = null) },
                label = { Text(text = stringResource(id = sections.title)) }
            )
        }
    }
}