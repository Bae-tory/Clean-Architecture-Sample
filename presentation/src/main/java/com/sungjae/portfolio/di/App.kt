package com.sungjae.portfolio.di

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.sungjae.portfolio.BuildConfig
import com.sungjae.portfolio.content.di.contentDataSourceModule
import com.sungjae.portfolio.data.di.dataModule
import com.sungjae.portfolio.extensions.setUpKoin
import com.sungjae.portfolio.local.di.localDataSourceModule
import com.sungjae.portfolio.remote.di.networkModule
import com.sungjae.portfolio.remote.di.remoteDataSourceModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        setUpKoin(
            this@App,
            viewModelModule,
            dataModule,
            useCaseModule,
            contentDataSourceModule,
            localDataSourceModule,
            remoteDataSourceModule,
            networkModule,
            dialogModule
        )

        setAndroidLogger()
    }

    private fun setAndroidLogger() {
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .methodCount(PRINT_STACK_COUNT)
            .build()

        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?) = BuildConfig.DEBUG
        })
    }

    companion object {
        private const val PRINT_STACK_COUNT: Int = 10
    }
}