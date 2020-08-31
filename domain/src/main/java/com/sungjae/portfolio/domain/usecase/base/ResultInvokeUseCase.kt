package com.sungjae.portfolio.domain.usecase.base

import com.sungjae.portfolio.domain.exception.Result

abstract class ResultInvokeUseCase<T, in Params> : BaseResultUseCase<Params>() {

    protected abstract fun buildUseCaseInvokeResult(): Result<T>?

    override suspend fun invoke(): Result<T> =
        buildUseCaseInvokeResult() ?: Result.OnError(Exception("InvokeUseCase Error"))

    override suspend fun execute(params: Params): Nothing? = null
}