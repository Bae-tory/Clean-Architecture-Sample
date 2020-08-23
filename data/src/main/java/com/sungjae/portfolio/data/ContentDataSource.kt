package com.sungjae.portfolio.data

interface ContentDataSource {

    fun getVersionName(): String
    fun getVersionCode(): Int
    fun getDeviceId(): String?
    fun getSDKVersion(): String
}