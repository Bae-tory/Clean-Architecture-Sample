package com.sungjae.portfolio.content.di

import android.content.Context
import com.sungjae.portfolio.content.ContentDataSourceImpl
import com.sungjae.portfolio.data.ContentDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ContentDataSourceModule {

    @Provides
    @Singleton
    fun provideContentModule(@ApplicationContext context: Context): ContentDataSource =
        ContentDataSourceImpl(context)
}