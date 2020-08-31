package com.sungjae.portfolio.domain.usecase.base

import com.sungjae.portfolio.domain.exception.InvalidSingleException
import io.reactivex.Scheduler
import io.reactivex.Single

abstract class SingleInputUseCase<T, in Params>(
    private val executorSchedulers: Scheduler,
    private val postExecutionScheduler: Scheduler
) : BaseSingleUseCase<Params>() {

    protected abstract fun buildUseCaseInputSingle(params: Params): Single<T>?

    override fun execute(params: Params): Single<T> =
        buildUseCaseInputSingle(params = params)
            ?.subscribeOn(executorSchedulers)
            ?.observeOn(postExecutionScheduler) ?: Single.error(InvalidSingleException())

    override fun invoke(): Nothing? = null
}