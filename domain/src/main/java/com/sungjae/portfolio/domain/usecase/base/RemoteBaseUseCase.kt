package com.sungjae.portfolio.domain.usecase.base

abstract class RemoteBaseUseCase<in Params> {

    abstract fun execute(params: Params): Any?

    abstract fun invoke(): Any?

}