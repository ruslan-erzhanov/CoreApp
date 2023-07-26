package com.erzhanov.coreapp.core.base.ui

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erzhanov.coreapp.core.base.extentions.onComplete
import com.erzhanov.coreapp.core.base.ui.model.UiEffect
import com.erzhanov.coreapp.core.base.ui.model.UiIntent
import com.erzhanov.coreapp.core.base.ui.model.UiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Base viewModel for Unidirectional Data Flow presentation framework.
 *
 * @param State screen state. Implementation of [UiState]
 * @param Intent screen intent. Implementation of [UiIntent]
 * @param Effect screen effect. Implementation of [UiEffect]
 */
abstract class MviViewModel<State : UiState, Intent : UiIntent, Effect : UiEffect>(
    initialUiState: State,
) : ViewModel() {

    protected open val handler = CoroutineExceptionHandler { _, exception ->
        onError(exception)
    }

    protected val scope: CoroutineScope
        get() = viewModelScope

    private val stateMutex = Mutex()

    /**
     * Screen [UiState] flow.
     */
    private val _uiState = MutableStateFlow(initialUiState)
    val uiState = _uiState.asStateFlow()

    /**
     * Screen [UiEffect] flow.
     */
    private val _uiEffect = MutableSharedFlow<Effect>()
    val uiEffect = _uiEffect.asSharedFlow()

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        scope.coroutineContext.cancelChildren()
        onProgressChanged(progress = false)
    }

    fun handleException(throwable: Throwable) {
        onError(throwable)
    }

    /**
     * Callback method that called if error occurred when [launch] method called.
     *
     * @param error instance of [Throwable]
     */
    open fun onError(error: Throwable) = Unit /* Ignore by default */

    /**
     * Callback method that called if progress changed when [launch] method called withProgress.
     *
     * @param progress indicates if progress running.
     */
    open fun onProgressChanged(progress: Boolean) = Unit /* Ignore by default */

    /**
     * Method to handle screen [UiIntent].
     * Can be used as lambda callback in composable functions.
     */
    abstract fun handleIntent(intent: Intent)

    /**
     * Method to emit single [UiEffect]
     */
    protected fun emitUiEffect(effect: Effect) {
        launch {
            _uiEffect.emit(effect)
        }
    }

    /**
     * Method for update [UiState] and emmit it
     */
    protected fun reduceState(block: State.() -> State) {
        launch {
            stateMutex.withLock {
                _uiState.emit(block(_uiState.value))
            }
        }
    }

    protected fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        withProgress: Boolean = false,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        if (withProgress) onProgressChanged(progress = true)
        return scope.launch(handler + context, block = block)
            .apply { if (withProgress) hideProgress() }
    }

    protected fun <T> Flow<T>.bind(action: suspend (value: T) -> Unit) = onEach(action).launchIn(scope)

    private fun Job.hideProgress(): Job {
        return onComplete { this@MviViewModel.onProgressChanged(progress = false) }
    }
}