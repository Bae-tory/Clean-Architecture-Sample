package com.sungjae.portfolio.domain.usecase

import com.sungjae.portfolio.domain.entity.request.ContentEntity
import com.sungjae.portfolio.domain.exception.InvalidTabTypeException
import com.sungjae.portfolio.domain.exception.Result
import com.sungjae.portfolio.domain.repository.Repository
import com.sungjae.portfolio.domain.usecase.base.ResultInputUseCase
import com.sungjae.portfolio.domain.usecase.base.SingleInputUseCase
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoadContentByHistoryUseCase(
    private val repository: Repository
) : ResultInputUseCase<ContentEntity, Pair<String, String>>() {


    override suspend fun buildUseCaseInputResult(params: Pair<String, String>): Result<ContentEntity>? {
        val tabName = params.first
        val query = params.second

        return when {
            tabName.isEmpty() -> Result.OnError(InvalidTabTypeException())
            else -> repository.getContents(type = tabName, query = query)
        }
    }
}