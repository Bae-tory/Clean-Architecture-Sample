package com.sungjae.portfolio.domain.exception

sealed class Result<out T> {
    data class OnSuccess<out T>(val data: T) : Result<T>()
    data class OnError(val exception: Exception) : Result<Nothing>()
}