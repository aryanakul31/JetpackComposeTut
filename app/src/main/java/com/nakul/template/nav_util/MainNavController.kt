package com.nakul.template.nav_util

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

class MainNavController(
    val navController: NavHostController,
) {

    fun upPress() {
        navController.navigateUp()
    }

    fun navigateLoginToHome(from: NavBackStackEntry) {
        //Abort if same view is trying to be opened
        if (NavRoutes.MAIN_CONTENT.route == navController.currentRoute())
            return
        //Abort if the current view is not in resumed state. Helps discard duplicated navigation events
        if (from.lifecycle.currentState != Lifecycle.State.RESUMED)
            return
        navController.navigate(NavRoutes.MAIN_CONTENT.route) {
            popUpTo(NavRoutes.LOGIN.route) {
                inclusive = true
            }
        }
    }
}