package com.nakul.template.bottom_util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nakul.template.nav_util.NavRoutes
import com.nakul.template.nav_util.currentRoute
import com.nakul.template.ui.presentation.home.Home


fun NavGraphBuilder.addMainContent() {
    composable(NavRoutes.HOME.route) {
        Home(text = NavRoutes.HOME.route)
    }
    composable(NavRoutes.POLL.route) {
        Home(text = NavRoutes.POLL.route)
    }
    composable(NavRoutes.PROFILE.route) {
        Home(text = NavRoutes.PROFILE.route)
    }
    composable(NavRoutes.SETTING.route) {
        Home(text = NavRoutes.SETTING.route)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavHelper(
    navController: NavHostController, content: @Composable (PaddingValues) -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val bottomNavigationItems = listOf<NavRoutes>(
        NavRoutes.HOME,
        NavRoutes.POLL,
        NavRoutes.PROFILE,
        NavRoutes.SETTING,
    )
    Scaffold(
        bottomBar = {
            val showNavigationBar = bottomNavigationItems.map { it.route }
                .contains(navBackStackEntry?.destination?.route)

            BottomNav(
                bottomNavigationItems, showNavigationBar, navController.currentRoute()
            ) { route ->
                navController.navigate(route)
            }
        }, content = content
    )
}

@Composable
fun BottomNav(
    bottomNavigationItems: List<NavRoutes>,
    showNavigationBar: Boolean,
    currentRoute: String?,
    navigateTo: (String) -> Unit
) {
    if (showNavigationBar) NavigationBar {
        bottomNavigationItems.forEach { screen ->
            NavigationBarItem(icon = {
                Icon(
                    tint = Color.Unspecified,
                    modifier = Modifier.size(40.dp),
                    painter = painterResource(if (currentRoute == screen.route) screen.iconSelected!! else screen.iconDefault!!),
                    contentDescription = stringResource(
                        id = screen.title
                    ),
                )
            },
                selected = currentRoute == screen.route,
                alwaysShowLabel = false, // This hides the title for the unselected items
                onClick = {
                    // This if check gives us a "singleTop" behavior where we do not create a
                    // second instance of the composable if we are already on that destination
                    if (currentRoute != screen.route) {
                        navigateTo(screen.route)
                    }
                })
        }
    }
}

@Preview
@Composable
fun PreviewBottomNav() {
    val bottomNavigationItems = listOf(
        NavRoutes.HOME,
        NavRoutes.POLL,
        NavRoutes.PROFILE,
        NavRoutes.SETTING,
    )

    BottomNav(bottomNavigationItems, true, NavRoutes.HOME.route) { route ->

    }
}