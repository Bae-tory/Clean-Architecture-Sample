package com.sungjae.portfolio.domain.repository

import com.sungjae.portfolio.domain.entity.request.ContentEntity
import com.sungjae.portfolio.domain.exception.Result


interface Repository {
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

    suspend fun getContents(type: String, query: String): Result<ContentEntity>
    suspend fun getContentsByHistory(type: String, query: String): Result<ContentEntity>
    suspend fun getContentQueries(type: String): Result<List<String>>
    suspend fun getCache(type: String): Result<ContentEntity>
}