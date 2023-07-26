package com.erzhanov.coreapp.core.base.ui.navigation

sealed class AppRoute {

    object Root: NavRoute.Simple(
        route = ROOT,
    )

    object MainScreen: NavRoute.Simple(
        route = MAIN,
    )

    object GetStartedScreen: NavRoute.Simple(
        route = GET_STARTED,
    )

    object SplashScreen: NavRoute.Simple(
        route = SPLASH,
    )

    object AuthScreen: NavRoute.Simple(
        route = AUTHENTICATION,
    )

    object Login : NavRoute.Simple(
        route = LOGIN,
    )

    object SignUp : NavRoute.Simple(
        route = SIGN_UP,
    )

    object Forgot : NavRoute.Simple(
        route = FORGOT_PASS,
    )

    object RedditList: NavRoute.Simple(
        route = REDDIT_LIST
    )

    object SavedRedditList: NavRoute.Simple(
        route = SAVED_REDDIT_LIST
    )

    companion object {
        private const val ROOT = "root_graph"
        private const val SPLASH = "splash_graph"
        private const val GET_STARTED = "get_started_graph"
        private const val MAIN = "main_graph"
        private const val AUTHENTICATION = "auth_graph"
        private const val LOGIN = "login"
        private const val SIGN_UP = "sign_up"
        private const val FORGOT_PASS = "forgot_password"
        private const val REDDIT_LIST = "reddit_list"
        private const val SAVED_REDDIT_LIST = "saved_reddit_list"
        const val REDDIT_LIST_IS_SAVED = "is_saved"
    }
}