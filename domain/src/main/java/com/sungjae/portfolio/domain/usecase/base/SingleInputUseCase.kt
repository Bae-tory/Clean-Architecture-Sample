package com.sungjae.portfolio.domain.usecase.base

import com.sungjae.portfolio.domain.exception.InvalidSingleException
import io.reactivex.Scheduler
import io.reactivex.Single

abstract class SingleInputUseCase<T, in Params> : BaseSingleUseCase<Params>() {

    protected abstract fun buildUseCaseInputSingle(params: Params): Single<T>?

    override fun execute(params: Params, executorScheduler: Scheduler, postExcutionScheduler: Scheduler): Single<T> =
        buildUseCaseInputSingle(params = params)
            ?.subscribeOn(executorScheduler)
            ?.observeOn(postExcutionScheduler) ?: Single.error(InvalidSingleException())

    override fun invoke(): Nothing? = null
}