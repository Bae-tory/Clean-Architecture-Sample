package com.sungjae.portfolio.domain.usecase.base

import io.reactivex.Scheduler

abstract class BaseSingleUseCase<in Params> {

    abstract fun execute(params: Params, executorScheduler: Scheduler, postExcutionScheduler: Scheduler): Any?

    abstract fun invoke(): Any?

}