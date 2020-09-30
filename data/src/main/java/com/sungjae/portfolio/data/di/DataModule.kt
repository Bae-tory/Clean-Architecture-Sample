package com.sungjae.portfolio.data.di

import com.sungjae.portfolio.data.ContentDataSource
import com.sungjae.portfolio.data.LocalDataSource
import com.sungjae.portfolio.data.RemoteDataSource
import com.sungjae.portfolio.data.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideRepository(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource, contentDataSource: ContentDataSource): RepositoryImpl =
        RepositoryImpl(localDataSource, remoteDataSource, contentDataSource)

}
