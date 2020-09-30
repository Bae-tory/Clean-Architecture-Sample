package com.sungjae.portfolio.di

import android.content.Context
import com.sungjae.portfolio.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

//@Module
//@InstallIn(ApplicationComponent::class)
//object DialogModule {
//
//    @Provides
//    @Singleton
//    fun provideConfirmDialog(@ApplicationContext context: Context, repository: Repository): ConfirmDialog = ConfirmDialog(context, repository)
//
//}