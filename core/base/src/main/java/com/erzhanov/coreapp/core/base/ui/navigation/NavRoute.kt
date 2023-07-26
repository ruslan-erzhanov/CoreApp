package com.erzhanov.coreapp.core.base.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink

sealed class NavRoute {
    abstract val route: String
    open val deepLinks: List<NavDeepLink> = emptyList()
    val routeDeclaration: String
        get() = when (this) {
            is WithArgs -> route + args.joinToString(
                separator = "/",
                prefix = "/",
                transform = { it.name },
            )

            else -> route
        }

    abstract class WithArgs(
        override val route: String,
    ) : NavRoute() {
        abstract val args: List<NamedNavArgument>
    }

    abstract class Simple(
        override val route: String,
    ) : NavRoute()
}