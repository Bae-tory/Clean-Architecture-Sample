package com.sungjae.portfolio.data.di

import com.sungjae.portfolio.data.RepositoryImpl
import com.sungjae.portfolio.domain.repository.Repository
import org.koin.dsl.module

val dataModule = module {

    single<Repository> { RepositoryImpl(get(), get(), get()) }
}