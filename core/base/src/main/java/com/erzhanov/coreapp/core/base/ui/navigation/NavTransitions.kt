@file:OptIn(ExperimentalAnimationApi::class)

package com.erzhanov.coreapp.core.base.ui.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.navigation.NavBackStackEntry

@OptIn(ExperimentalAnimationApi::class)
data class NavTransitions(
    val enterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition),
    val exitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition),
    val popEnterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition),
    val popExitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition)
) {
    companion object {


        fun none() = NavTransitions(
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None }
        )

        fun fade(
            animationSpec: FiniteAnimationSpec<Float> = spring(stiffness = Spring.StiffnessMediumLow),
        ) = NavTransitions(
            enterTransition = { fadeIn(animationSpec) },
            exitTransition = { fadeOut(animationSpec) },
            popEnterTransition = { fadeIn(animationSpec) },
            popExitTransition = { fadeOut(animationSpec) }
        )

        fun horizontalSlide() = NavTransitions(
            enterTransition = { slideIntoContainer(towards = AnimatedContentScope.SlideDirection.Start) },
            exitTransition = { slideOutOfContainer(towards = AnimatedContentScope.SlideDirection.Start) },
            popEnterTransition = { slideIntoContainer(towards = AnimatedContentScope.SlideDirection.End) },
            popExitTransition = { slideOutOfContainer(towards = AnimatedContentScope.SlideDirection.End) }
        )

        fun slideFromBottom() = NavTransitions(
            enterTransition = {
                slideIntoContainer(towards = AnimatedContentScope.SlideDirection.Up)
            },
            exitTransition = {
                scaleOut(targetScale = 0.9f) + fadeOut()
            },
            popEnterTransition = {
                scaleIn(initialScale = 0.9f) + fadeIn()
            },
            popExitTransition = {
                slideOutOfContainer(towards = AnimatedContentScope.SlideDirection.Down)
            }
        )
    }
}