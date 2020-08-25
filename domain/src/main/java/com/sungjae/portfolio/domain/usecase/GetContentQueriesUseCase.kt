package com.sungjae.portfolio.domain.usecase

import com.sungjae.portfolio.domain.exception.InvalidSingleException
import com.sungjae.portfolio.domain.repository.Repository
import com.sungjae.portfolio.domain.usecase.base.SingleInputUseCase
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetContentQueriesUseCase(
    private val repository: Repository,
    executorScheduler: Scheduler = Schedulers.io(),
    postExecutionScheduler: Scheduler = AndroidSchedulers.mainThread()
) : SingleInputUseCase<List<String>, String>(executorScheduler, postExecutionScheduler) {

    override fun buildUseCaseInputSingle(params: String): Single<List<String>> {
        val tabName = params
        return when {
            tabName.isEmpty() -> Single.error(InvalidSingleException())
            else -> repository.getContentQueries(params)
        }
    }
}