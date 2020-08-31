package com.sungjae.portfolio.domain.usecase.base

import com.sungjae.portfolio.domain.exception.Result

abstract class ResultInputUseCase<T, in Params> : BaseResultUseCase<Params>() {

    protected abstract suspend fun buildUseCaseInputResult(params: Params): Result<T>?

    override suspend fun execute(params: Params): Result<T> =
        buildUseCaseInputResult(params = params) ?: run { Result.OnError(Exception("InputUseCase Error")) }

    override suspend fun invoke(): Nothing? = null
}