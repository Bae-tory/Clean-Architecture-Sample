package com.sungjae.portfolio.content

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import android.provider.Settings
import com.sungjae.portfolio.data.ContentDataSource

@SuppressLint("HardwareIds")
class ContentDataSourceImpl(private val context: Application) : ContentDataSource {

    override fun getDeviceId(): String? {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

    override fun getVersionName(): String = BuildConfig.VERSION_NAME

    override fun getVersionCode(): Int = BuildConfig.VERSION_CODE

    override fun getSDKVersion() = Build.VERSION.SDK_INT.toString()
}