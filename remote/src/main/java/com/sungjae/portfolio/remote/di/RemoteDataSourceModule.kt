package com.sungjae.portfolio.remote.di

import com.sungjae.portfolio.data.RemoteDataSource
import com.sungjae.portfolio.remote.RemoteDataSourceImpl

val remoteDataSourceModule = module {

    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }

}