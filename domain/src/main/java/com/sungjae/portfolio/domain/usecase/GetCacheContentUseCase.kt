package com.sungjae.portfolio.domain.usecase

import com.sungjae.portfolio.domain.entity.request.ContentEntity
import com.sungjae.portfolio.domain.exception.InvalidTabTypeException
import com.sungjae.portfolio.domain.repository.Repository
import com.sungjae.portfolio.domain.usecase.base.SingleInputUseCase
import io.reactivex.Single

class GetCacheContentUseCase(
    private val repository: Repository
) : SingleInputUseCase<ContentEntity, String>() {

    override fun buildUseCaseInputSingle(params: String): Single<ContentEntity>? {
        val tabName = params

        return when {
            tabName.isBlank() -> Single.error(InvalidTabTypeException())
            else -> repository.getCache(params)
        }
    }
}