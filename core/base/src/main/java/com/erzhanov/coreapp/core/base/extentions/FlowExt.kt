package com.erzhanov.coreapp.core.base.extentions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CompletionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class InitialValueFlow<T>(private val flow: Flow<T>) : Flow<T> by flow {
    fun dropInitialValue(): Flow<T> = drop(1)
}

fun <T> Flow<T>.asInitialValueFlow(value: T): InitialValueFlow<T> = InitialValueFlow(
    onStart { emit(value) }
)

fun <T> Flow<T>.bind(
    scope: CoroutineScope,
    action: (suspend (value: T) -> Unit)? = null
) = apply { action?.let(::onEach) }.launchIn(scope)

suspend inline fun <T> Flow<T>.collectInlined(crossinline action: suspend (value: T) -> Unit): Unit =
    collect { value -> action(value) }

inline fun <T> Flow<T>.collectWhen(
    owner: LifecycleOwner,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline action: suspend (value: T) -> Unit
): Job = owner.run {
        lifecycleScope.launch {
            repeatOnLifecycle(state) {
                this@collectWhen.collectInlined(action)
            }
        }
    }
fun Job.onComplete(completionHandler: CompletionHandler): Job {
    return this@onComplete.also { invokeOnCompletion(completionHandler) }
}