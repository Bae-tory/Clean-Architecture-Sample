package com.sungjae.portfolio.domain.usecase

import com.sungjae.portfolio.domain.entity.request.Request
import com.sungjae.portfolio.domain.repository.Repository
import com.sungjae.portfolio.domain.usecase.base.SingleInputUseCase
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CheckDeviceUseCase(
    private val repository: Repository,
    executorScheduler: Scheduler = Schedulers.io(),
    postExecutionScheduler: Scheduler = AndroidSchedulers.mainThread()
) : SingleInputUseCase<Request, String>(executorScheduler, postExecutionScheduler) {

    override fun buildUseCaseInputSingle(params: String): Single<Request> {
        return when {
            else -> repository.checkDevice(Request())
        }
    }
}