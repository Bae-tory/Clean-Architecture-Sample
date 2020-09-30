package com.sungjae.portfolio.di

import com.sungjae.portfolio.domain.repository.Repository
import com.sungjae.portfolio.domain.usecase.GetCacheContentUseCase
import com.sungjae.portfolio.domain.usecase.GetContentQueriesUseCase
import com.sungjae.portfolio.domain.usecase.GetContentUseCase
import com.sungjae.portfolio.domain.usecase.LoadContentByHistoryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object UserCaseModule {

    @Provides
    @Singleton
    fun provideGetContentUseCase(repository: Repository): GetContentUseCase = GetContentUseCase(repository)

    @Provides
    @Singleton
    fun provideGetCacheContentUseCase(repository: Repository): GetCacheContentUseCase = GetCacheContentUseCase(repository)

    @Provides
    @Singleton
    fun provideLoadContentByHistoryUseCase(repository: Repository): LoadContentByHistoryUseCase = LoadContentByHistoryUseCase(repository)

    @Provides
    @Singleton
    fun provideGetContentQueriesUseCase(repository: Repository): GetContentQueriesUseCase = GetContentQueriesUseCase(repository)

}