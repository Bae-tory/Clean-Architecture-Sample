package com.sungjae.portfolio.domain.usecase

import com.sungjae.portfolio.domain.exception.InvalidSingleException
import com.sungjae.portfolio.domain.exception.Result
import com.sungjae.portfolio.domain.repository.Repository
import com.sungjae.portfolio.domain.usecase.base.ResultInputUseCase

class GetContentQueriesUseCase(
    private val repository: Repository
) : ResultInputUseCase<List<String>, String>() {


    override suspend fun buildUseCaseInputResult(params: String): Result<List<String>>? {
        val tabName = params
        return when {
            tabName.isEmpty() -> Result.OnError(InvalidSingleException())
            else -> repository.getContentQueries(params)
        }
    }
}