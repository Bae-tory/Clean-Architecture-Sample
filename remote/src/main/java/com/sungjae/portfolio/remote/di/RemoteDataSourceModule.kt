package com.sungjae.portfolio.remote.di

import com.sungjae.portfolio.data.RemoteDataSource
import com.sungjae.portfolio.remote.RemoteDataSourceImpl
import com.sungjae.portfolio.remote.component.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSoruce(api: Api): RemoteDataSource =
        RemoteDataSourceImpl(api)
}
