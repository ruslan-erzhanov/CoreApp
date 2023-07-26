package com.erzhanov.coreapp.ui.navigation.bottommenu

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.erzhanov.coreapp.core.base.R
import com.erzhanov.coreapp.core.base.ui.navigation.AppRoute

sealed class BottomNavItem(@StringRes val title: Int, val icon: ImageVector, val route: String) {
    object Explore : BottomNavItem(R.string.explore_title, Icons.Filled.Search, AppRoute.RedditList.route)
    object Saved : BottomNavItem(R.string.saved_title, Icons.Filled.FavoriteBorder, AppRoute.SavedRedditList.route)
}