package com.sungjae.portfolio.local.di

import android.app.Application
import android.content.SharedPreferences
import com.sungjae.portfolio.data.LocalDataSource
import com.sungjae.portfolio.local.LocalDataSourceImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localDataSourceModule = module {

    single<SharedPreferences> {
        getSharedPref(androidApplication())
    }

    single<LocalDataSource> {
        LocalDataSourceImpl(get())
    }
}

fun getSharedPref(androidApplication: Application): SharedPreferences {
    return androidApplication.getSharedPreferences("pref", android.content.Context.MODE_PRIVATE)
}