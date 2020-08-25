package com.sungjae.portfolio.domain.usecase

import com.sungjae.portfolio.domain.entity.request.ContentEntity
import com.sungjae.portfolio.domain.exception.InvalidTabTypeException
import com.sungjae.portfolio.domain.repository.Repository
import com.sungjae.portfolio.domain.usecase.base.SingleInputUseCase
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetCacheContentUseCase(
    private val repository: Repository,
    executorScheduler: Scheduler = Schedulers.io(),
    postExecutionScheduler: Scheduler = AndroidSchedulers.mainThread()
) : SingleInputUseCase<ContentEntity, String>(executorScheduler, postExecutionScheduler) {

    override fun buildUseCaseInputSingle(params: String): Single<ContentEntity>? {
        val tabName = params

        return when {
            tabName.isBlank() -> Single.error(InvalidTabTypeException())
            else -> repository.getCache(params)
        }
    }
}