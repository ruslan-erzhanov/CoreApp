package com.erzhanov.coreapp.ui.navigation.graphs

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.ImageLoader
import com.erzhanov.coreapp.auth.ui.splash.GetStartedScreen
import com.erzhanov.coreapp.auth.ui.splash.SplashScreen
import com.erzhanov.coreapp.auth.ui.splash.SplashViewModel
import com.erzhanov.coreapp.core.base.ui.navigation.AppRoute
import com.erzhanov.coreapp.core.base.ui.navigation.NavTransitions
import com.erzhanov.coreapp.ui.main.MainScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    imageLoader: ImageLoader,
) {
    AnimatedNavHost(
        navController = navController,
        route = AppRoute.Root.routeDeclaration,
        startDestination = AppRoute.SplashScreen.route,
    ) {
        val horizontalSlide = NavTransitions.horizontalSlide()
        val fade = NavTransitions.fade()
        composable(
            route = AppRoute.SplashScreen.routeDeclaration,
            exitTransition = fade.exitTransition,
        ) {
            val splashViewModel: SplashViewModel = hiltViewModel()
            SplashScreen(
                effects = splashViewModel.uiEffect,
                intents = splashViewModel::handleIntent,
                goToStart = {
                    navController.popBackStack()
                    navController.navigate(AppRoute.GetStartedScreen.route)
                },
                goToMain = {
                    navController.popBackStack()
                    navController.navigate(AppRoute.MainScreen.route)
                }
            )
        }

        composable(
            route = AppRoute.GetStartedScreen.routeDeclaration,
            exitTransition = horizontalSlide.exitTransition,
            enterTransition = horizontalSlide.enterTransition,
            popEnterTransition = horizontalSlide.popEnterTransition,
            popExitTransition = horizontalSlide.popExitTransition,
        ) {
            val splashViewModel: SplashViewModel = hiltViewModel()
            val splashState = splashViewModel.uiState.collectAsState()
            GetStartedScreen(
                state = splashState.value,
                effects = splashViewModel.uiEffect,
                intents = splashViewModel::handleIntent,
                goAuth = {
                    navController.navigate(AppRoute.AuthScreen.route)
                },
                goToMain = {
                    navController.popBackStack()
                    navController.navigate(AppRoute.MainScreen.route)
                }
            )
        }

        authNavGraph(navController = navController)
        composable(
            route = AppRoute.MainScreen.routeDeclaration,
        ) {
            MainScreen(imageLoader = imageLoader)
        }
    }
}