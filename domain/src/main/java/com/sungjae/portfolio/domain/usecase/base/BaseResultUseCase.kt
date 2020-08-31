package com.sungjae.portfolio.domain.usecase.base

abstract class BaseResultUseCase<in Params> {

    abstract suspend fun execute(params: Params): Any?

    abstract suspend fun invoke(): Any?

}