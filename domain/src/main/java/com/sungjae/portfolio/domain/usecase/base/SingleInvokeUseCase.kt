package com.sungjae.portfolio.domain.usecase.base

import com.sungjae.portfolio.domain.exception.InvalidSingleException
import io.reactivex.Scheduler
import io.reactivex.Single

abstract class SingleInvokeUseCase<T, in Params>(
    private val executorSchedulers: Scheduler,
    private val postExecutionScheduler: Scheduler
) : BaseSingleUseCase<Params>() {

    protected abstract fun buildUseCaseInvokeSingle(): Single<T>?

    override fun invoke(): Single<T> =
        buildUseCaseInvokeSingle()
            ?.subscribeOn(executorSchedulers)
            ?.observeOn(postExecutionScheduler) ?: Single.error(InvalidSingleException())

    override fun execute(params: Params): Nothing? = null
}