package com.sungjae.portfolio.domain.usecase

import com.sungjae.portfolio.domain.exception.InvalidSingleException
import com.sungjae.portfolio.domain.repository.Repository
import com.sungjae.portfolio.domain.usecase.base.SingleInputUseCase
import io.reactivex.Single

class GetContentQueriesUseCase(
    private val repository: Repository
) : SingleInputUseCase<List<String>, String>() {

    override fun buildUseCaseInputSingle(params: String): Single<List<String>> {
        val tabName = params
        return when {
            tabName.isEmpty() -> Single.error(InvalidSingleException())
            else -> repository.getContentQueries(params)
        }
    }
}