package com.sungjae.portfolio.domain.exception

import io.reactivex.Single

sealed class Result<out T> {

    data class OnSuccess<out T>(val data: T) : Result<T>()

    data class OnError<out T>(val error: Error) : Result<T>()
}

data class Error(
    val code: Int,
    val message: String? = null,
    val throwable: Throwable? = null
)

class ErrorThrowable(
    private val code: Int,
    override val message: String? = null,
    private val throwable: Throwable? = null
) : Throwable(message) {
    fun toError(): Error = Error(code, message, throwable)
}

fun Error.toThrowable(): ErrorThrowable = ErrorThrowable(code, message, throwable)

fun <T> Throwable.toErrorResult(): Result<T> =
    when (this) {
        is ErrorThrowable -> Result.OnError(this.toError())
        else -> Result.OnError(ErrorThrowable(UNKNOWN, message, this).toError())
    }


fun <T> Result<T>.toDataSingle(): Single<T> =
    when (this) {
        is Result.OnSuccess -> Single.just(this.data)
        is Result.OnError -> Single.error(this.error.toThrowable())
    }

fun <T> Result<T>.toSingle(): Single<Result<T>> =
    when (this) {
        is Result.OnSuccess -> Single.just(this)
        is Result.OnError -> Single.error(this.error.toThrowable())
    }

fun <T> T.toResult(): Result<T> = Result.OnSuccess(this)

fun <T> Error.toResult(): Result<T> = Result.OnError(this)

// unknown error
const val UNKNOWN = 99000