package com.sungjae.portfolio.domain.repository

import com.sungjae.portfolio.domain.entity.request.Content
import io.reactivex.Single


interface Repository {
    //Local
    fun getString(key: String): String?
    fun getInt(key: String): Int
    fun getLong(key: String): Long
    fun getBoolean(key: String): Boolean

    fun putBoolean(key: String, data: Boolean)
    fun putString(key: String, data: String)
    fun putInt(key: String, data: Int)
    fun putLong(key: String, data: Long)

    fun clearData()

    //Content
    fun getVersionName(): String
    fun getVersionCode(): Int
    fun getDeviceId(): String?
    fun getSDKVersion(): String

    //Remote
    fun getContents(type: String, query: String): Single<Content>
    fun getContentsByHistory(type: String, query: String): Single<Content>
    fun getContentQueries(type: String): Single<List<String>>
    fun getCache(type: String): Single<Content>
}