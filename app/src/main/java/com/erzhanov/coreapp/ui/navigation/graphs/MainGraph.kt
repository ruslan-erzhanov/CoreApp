package com.erzhanov.coreapp.ui.navigation.graphs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.ImageLoader
import com.erzhanov.coreapp.core.base.ui.navigation.AppRoute
import com.erzhanov.coreapp.reddits.ui.reddits.RedditListViewModel
import com.erzhanov.coreapp.reddits.ui.reddits.RedditsListScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainGraph(
    navController: NavHostController,
    imageLoader: ImageLoader,
    padding: PaddingValues
) {
    AnimatedNavHost(
        modifier = Modifier.padding(padding),
        navController = navController,
        startDestination = AppRoute.RedditList.route,
    ) {
        composable(AppRoute.RedditList.routeDeclaration) {
            val viewModel: RedditListViewModel = hiltViewModel()
            val state = viewModel.uiState.collectAsState()
            RedditsListScreen(
                state = state.value,
                effects = viewModel.uiEffect,
                intents = viewModel::handleIntent,
                imageLoader = imageLoader,
                savedOnly = false
            )
        }

        composable(AppRoute.SavedRedditList.routeDeclaration) {
            val viewModel: RedditListViewModel = hiltViewModel()
            val state = viewModel.uiState.collectAsState()
            RedditsListScreen(
                state = state.value,
                effects = viewModel.uiEffect,
                intents = viewModel::handleIntent,
                imageLoader = imageLoader,
                savedOnly = true
            )
        }
    }
}