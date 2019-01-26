package io.smallant.wizard.data.sources.remote

import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

suspend fun <T : Any> Result<T>.await(): T {
    return suspendCoroutine { continuation ->
        when (this) {
            is Result.Success -> continuation.resume(this.data)
            is Result.Error -> continuation.resumeWithException(this.exception)
        }
    }
}