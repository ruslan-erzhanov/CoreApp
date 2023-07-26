package com.erzhanov.coreapp.ui.navigation.graphs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.erzhanov.coreapp.auth.ui.auth.LoginScreen
import com.erzhanov.coreapp.auth.ui.auth.SimpleScreen
import com.erzhanov.coreapp.core.base.ui.navigation.AppRoute
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = AppRoute.AuthScreen.routeDeclaration,
        startDestination = AppRoute.Login.route
    ) {
        composable(route = AppRoute.Login.routeDeclaration) {
            LoginScreen(
                onClick = {
                    navController.navigate(AppRoute.MainScreen.route) {
                        popUpTo(AppRoute.GetStartedScreen.route) {
                            inclusive = true
                        }
                    }
                },
                onSignUpClick = {
                    navController.navigate(AppRoute.SignUp.route)
                },
                onForgotClick = {
                    navController.navigate(AppRoute.Forgot.route)
                }
            )
        }
        composable(route = AppRoute.SignUp.routeDeclaration) {
            SimpleScreen(name = AppRoute.SignUp.route) {
                navController.navigate(AppRoute.MainScreen.route) {
                    popUpTo(AppRoute.GetStartedScreen.route) {
                        inclusive = true
                    }
                }
            }
        }
        composable(route = AppRoute.Forgot.routeDeclaration) {
            SimpleScreen(name = AppRoute.Forgot.route) {
                navController.navigate(AppRoute.MainScreen.route) {
                    popUpTo(AppRoute.GetStartedScreen.route) {
                        inclusive = true
                    }
                }
            }
        }
    }
}