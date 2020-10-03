package com.sungjae.portfolio.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.sungjae.portfolio.data.LocalDataSource
import com.sungjae.portfolio.local.LocalDataSourceImpl
import com.sungjae.portfolio.local.room.ContentDao
import com.sungjae.portfolio.local.room.ContentDataBase
import com.sungjae.portfolio.local.room.ContentDataBase.Companion.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object LocalDataSourceModule {
    @Provides
    @Singleton
    fun provideContentDataBase(@ApplicationContext context: Context): ContentDataBase =
        Room.databaseBuilder(context, ContentDataBase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideContentDao(contentDataBase: ContentDataBase): ContentDao =
        contentDataBase.contentDao()

    @Provides
    @Singleton
    fun provideLocalDataSoruce(pref: SharedPreferences, contentDao: ContentDao): LocalDataSource =
        LocalDataSourceImpl(pref, contentDao)

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("pref", Context.MODE_PRIVATE)
}