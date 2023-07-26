package com.erzhanov.coreapp.reddits.model


import com.erzhanov.coreapp.core.base.ui.model.ListItem
import com.erzhanov.coreapp.core.base.ui.model.ProgressItem
import com.erzhanov.coreapp.core.base.ui.model.UiEffect
import com.erzhanov.coreapp.core.base.ui.model.UiIntent
import com.erzhanov.coreapp.core.base.ui.model.UiState

data class RedditListState(
    val inProgress: Boolean = true,
    val items: List<ListItem> = listOf(ProgressItem("1"), ProgressItem("2"), ProgressItem("3"), ProgressItem("4")),
    val nextPageToken: String = "",
) : UiState {
    val hasNext get() = nextPageToken.isNotEmpty()
}

sealed class RedditListIntent: UiIntent {
    data class GetInitialItems(val savedOnly: Boolean) : RedditListIntent()
    data class GetNextItems(val nextPageToken: String) : RedditListIntent()
    data class SaveReddit(val reddit: AppReddit) : RedditListIntent()
    data class RemoveReddit(val reddit: AppReddit) : RedditListIntent()
}

sealed class RedditListEffect: UiEffect {

    object OnError : RedditListEffect()
}