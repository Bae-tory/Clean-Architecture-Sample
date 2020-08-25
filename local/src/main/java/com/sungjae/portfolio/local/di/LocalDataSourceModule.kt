package com.sungjae.portfolio.local.di

import android.app.Application
import android.content.SharedPreferences
import androidx.room.Room
import com.sungjae.portfolio.data.LocalDataSource
import com.sungjae.portfolio.local.LocalDataSourceImpl
import com.sungjae.portfolio.local.room.ContentDataBase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localDataSourceModule = module {

    single<SharedPreferences> {
        getSharedPref(androidApplication())
    }

    single { Room.databaseBuilder(androidApplication(), ContentDataBase::class.java, "Contents.db").build() }

    single { get<ContentDataBase>().contentDao() }

    single<LocalDataSource> {
        LocalDataSourceImpl(get(), get())
    }
}

fun getSharedPref(androidApplication: Application): SharedPreferences {
    return androidApplication.getSharedPreferences("pref", android.content.Context.MODE_PRIVATE)
}