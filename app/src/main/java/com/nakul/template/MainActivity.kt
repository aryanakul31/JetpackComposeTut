package com.nakul.template

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.nakul.template.bottom_util.BottomNavHelper
import com.nakul.template.bottom_util.addMainContent
import com.nakul.template.nav_util.MainNavController
import com.nakul.template.nav_util.NavRoutes
import com.nakul.template.ui.presentation.login.LoginContent
import com.nakul.template.ui.presentation.login.LoginVM
import com.nakul.template.ui.theme.JetpackComposeTemplateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), IMainActivity {
    companion object {
        var iMainActivity: IMainActivity? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iMainActivity = this
        setContent {
            val navHost = rememberNavController()
            val mainNavController = remember(navHost) {
                MainNavController(navHost)
            }
            JetpackComposeTemplateTheme {
                BottomNavHelper(navController = mainNavController.navController) {
                    NavHost(
                        navController = mainNavController.navController,
                        startDestination = NavRoutes.LOGIN.route
                    ) {
                        composable(
                            route = NavRoutes.LOGIN.route, arguments = listOf(navArgument("email") {
                                type = NavType.StringType
                                nullable = false
                                defaultValue = ""
                            })
                        ) { from ->

                            val viewModel by viewModels<LoginVM>()
                            LoginContent(email = viewModel.email,
                                password = viewModel.password,
                                emailError = viewModel.emailError.value,
                                passwordError = viewModel.passwordError.value,
                                rememberMe = viewModel.rememberMe,
                                clickSignIn = {
                                    viewModel.handleRemeberMe()
                                    mainNavController.navigateLoginToHome(from)
                                })
                        }

                        navigation(
                            route = NavRoutes.MAIN_CONTENT.route,
                            startDestination = NavRoutes.HOME.route
                        ) {
                            addMainContent()
                        }
                    }
                }
            }
        }
    }

    override fun getActivity(): Activity = this
}