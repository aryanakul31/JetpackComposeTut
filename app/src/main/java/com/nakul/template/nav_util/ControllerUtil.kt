package com.nakul.template.nav_util

import androidx.navigation.NavHostController

fun NavHostController.currentRoute(): String? =
    currentBackStackEntry?.destination?.route