package com.nakul.template.nav_util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NamedNavArgument
import com.nakul.template.R

enum class NavRoutes(
    @StringRes val title: Int,
    val route: String,
    @DrawableRes val iconSelected: Int? = null,
    @DrawableRes val iconDefault: Int? = null,
    val args: List<NamedNavArgument> = emptyList(),
) {
    LOGIN(R.string.login, "bottom/login"),
    MAIN_CONTENT(R.string.login, "bottom/main_content"),

    HOME(
        R.string.home,
        "bottom/home",
        iconSelected = R.drawable.bottom_home_selected,
        iconDefault = R.drawable.bottom_home_unselected
    ),
    POLL(
        R.string.poll,
        "bottom/poll",
        iconSelected = R.drawable.bottom_polls_selected,
        iconDefault = R.drawable.bottom_polls_unselected
    ),
    PROFILE(
        R.string.profile,
        "bottom/profile",
        iconSelected = R.drawable.bottom_profile_selected,
        iconDefault = R.drawable.bottom_profile_unselected
    ),
    SETTING(
        R.string.setting,
        "bottom/setting",
        iconSelected = R.drawable.bottom_settings_selected,
        iconDefault = R.drawable.bottom_settings_unselected
    ),
}