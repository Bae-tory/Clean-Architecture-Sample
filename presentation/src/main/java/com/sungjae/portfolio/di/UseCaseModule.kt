package com.sungjae.portfolio.di

import com.sungjae.portfolio.domain.usecase.GetCacheContentUseCase
import com.sungjae.portfolio.domain.usecase.GetContentQueriesUseCase
import com.sungjae.portfolio.domain.usecase.GetContentUseCase
import com.sungjae.portfolio.domain.usecase.LoadContentByHistory
import org.koin.dsl.module

val useCaseModule = module {

    factory { GetContentUseCase(get()) }
    factory { GetCacheContentUseCase(get()) }
    factory { LoadContentByHistory(get()) }
    factory { GetContentQueriesUseCase(get()) }
}