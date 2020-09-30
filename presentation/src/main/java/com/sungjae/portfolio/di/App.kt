package com.sungjae.portfolio.di

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.sungjae.portfolio.BuildConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
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