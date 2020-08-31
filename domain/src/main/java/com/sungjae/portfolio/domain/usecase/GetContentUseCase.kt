package com.sungjae.portfolio.domain.usecase

import com.sungjae.portfolio.domain.entity.request.ContentEntity
import com.sungjae.portfolio.domain.exception.InvalidQueryBlankException
import com.sungjae.portfolio.domain.exception.InvalidQueryException
import com.sungjae.portfolio.domain.exception.InvalidTabTypeException
import com.sungjae.portfolio.domain.exception.Result
import com.sungjae.portfolio.domain.repository.Repository
import com.sungjae.portfolio.domain.usecase.base.ResultInputUseCase

class GetContentUseCase(
    private val repository: Repository
) : ResultInputUseCase<ContentEntity, Pair<String, String?>>() {

    override suspend fun buildUseCaseInputResult(params: Pair<String, String?>): Result<ContentEntity>? {
        val tabName = params.first
        val query = params.second

        return when {
            tabName.isEmpty() -> Result.OnError(InvalidTabTypeException())
            query == null -> Result.OnError(InvalidQueryException())
            query.isBlank() -> Result.OnError(InvalidQueryBlankException())
            else -> repository.getContents(type = tabName, query = query)
        }
    }
}