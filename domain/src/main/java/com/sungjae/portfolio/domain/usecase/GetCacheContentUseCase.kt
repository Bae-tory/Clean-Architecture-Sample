package com.sungjae.portfolio.domain.usecase

import com.sungjae.portfolio.domain.entity.request.ContentEntity
import com.sungjae.portfolio.domain.exception.InvalidTabTypeException
import com.sungjae.portfolio.domain.exception.Result
import com.sungjae.portfolio.domain.repository.Repository
import com.sungjae.portfolio.domain.usecase.base.ResultInputUseCase

class GetCacheContentUseCase(
    private val repository: Repository
) : ResultInputUseCase<ContentEntity, String>() {


    override suspend fun buildUseCaseInputResult(params: String): Result<ContentEntity>? {
        val tabName = params
        return when {
            tabName.isBlank() -> Result.OnError(InvalidTabTypeException())
            else -> repository.getCache(params)
        }
    }
}