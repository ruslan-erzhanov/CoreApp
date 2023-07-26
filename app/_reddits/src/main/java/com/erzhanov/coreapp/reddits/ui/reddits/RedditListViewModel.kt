package com.erzhanov.coreapp.reddits.ui.reddits

import com.erzhanov.coreapp.core.base.mappers.mapList
import com.erzhanov.coreapp.core.base.ui.MviViewModel
import com.erzhanov.coreapp.core.base.ui.model.ListItem
import com.erzhanov.coreapp.core.base.ui.model.PlaceHolderItem
import com.erzhanov.coreapp.core.base.ui.model.ProgressItem
import com.erzhanov.coreapp.data.common.Logger
import com.erzhanov.coreapp.data.reddits.RedditsUseCase
import com.erzhanov.coreapp.data.reddits.model.Reddit
import com.erzhanov.coreapp.reddits.model.AppReddit
import com.erzhanov.coreapp.reddits.model.RedditListEffect
import com.erzhanov.coreapp.reddits.model.RedditListIntent
import com.erzhanov.coreapp.reddits.model.RedditListState
import com.erzhanov.coreapp.reddits.model.asApp
import com.erzhanov.coreapp.reddits.model.asDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class RedditListViewModel
@Inject
constructor(
    private val redditsUseCase: RedditsUseCase,
    private val logger: Logger,
) : MviViewModel<RedditListState, RedditListIntent, RedditListEffect>(RedditListState()) {

    override fun handleIntent(intent: RedditListIntent) {
        when (intent) {
            is RedditListIntent.GetInitialItems -> {
                if (intent.savedOnly.not()) loadList()
                subscribeOnSaved(intent.savedOnly)
            }

            is RedditListIntent.GetNextItems -> loadListNext(intent.nextPageToken)
            is RedditListIntent.RemoveReddit -> removeReddit(intent.reddit.id)
            is RedditListIntent.SaveReddit -> saveReddit(intent.reddit.asDomain)
        }
    }

    override fun onError(error: Throwable) {
        logger.log("${error.message}")
        when {
            uiState.value.items.firstOrNull() is ProgressItem ->
                reduceState {
                    copy(
                        inProgress = false,
                        items = listOf(PlaceHolderItem.Error()),
                    )
                }
            else -> {
                emitUiEffect(RedditListEffect.OnError)
            }
        }
    }

    private fun loadListNext(nextPage: String) {
        reduceState {
            copy(inProgress = false)
        }
        loadList(nextPage)
    }

    private fun loadList(nextPage: String = "") {
        launch {
            val page = redditsUseCase.fetchReddits(nextPage)
            reduceState {
                copy(
                    inProgress = false,
                    items = items.merge(page.items, page.token.isNotEmpty()),
                    nextPageToken = page.token,
                )
            }
        }
    }

    private fun saveReddit(reddit: Reddit) {
        launch(Dispatchers.IO) {
            redditsUseCase.saveReddit(reddit)
        }
    }

    private fun removeReddit(id: String) {
        launch(Dispatchers.IO) {
            redditsUseCase.deleteReddit(id)
        }
    }

    private fun List<ListItem>.updateSavedState(ids: List<String>) = mapList {
        when (it) {
            is AppReddit -> it.copy(saved = ids.contains(it.id))
            else -> it
        }
    }

    private fun subscribeOnSaved(savedOnly: Boolean) {
        redditsUseCase.getSavedReddits().bind { list ->
            if (savedOnly.not() && uiState.value.inProgress) return@bind
            reduceState {
                copy(
                    items = when {
                        savedOnly -> list.map(Reddit::asApp)
                        else -> {
                            val ids = list.map { it.id }
                            items.updateSavedState(ids)
                        }
                    }
                )
            }
        }
    }

    private fun List<ListItem>.merge(items: List<Reddit>, hasNext: Boolean) : List<ListItem> {
        val filtered = filterIsInstance<AppReddit>().toSet() + items.map(Reddit::asApp)
        return when {
            hasNext -> filtered + ProgressItem("")
            else -> filtered
        }.toList()
    }
}