package com.erzhanov.coreapp.core.base.extentions

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job

fun tryOrIgnore(action: () -> Unit) {
    try {
        action()
    } catch (ignore: Exception) { }
}

fun <T> tryOrNull(action: () -> T): T? {
    return try {
        action()
    } catch (ignore: Exception) {
        return null
    }
}

fun <T> tryOr(action: () -> T, error: (Exception) -> T): T {
    return try {
        action()
    } catch (exception: Exception) {
        return error(exception)
    }
}

suspend fun <T> tryAwaitOr(action: suspend () -> Deferred<T>): T? {
    return try {
        action().await()
    } catch (ignore: Exception) {
        return null
    }
}

suspend fun tryJobOrIgnore(action: Job) {
    return try {
       action.join()
    } catch (ignore: Exception) { }
}

val Boolean?.orFalse: Boolean
    get() = this ?: false