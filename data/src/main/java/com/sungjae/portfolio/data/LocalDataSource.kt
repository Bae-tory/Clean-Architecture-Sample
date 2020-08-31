package com.sungjae.portfolio.data

import com.sungjae.portfolio.data.models.Content
import com.sungjae.portfolio.domain.entity.request.ContentEntity
import com.sungjae.portfolio.domain.exception.Result


interface LocalDataSource {

    fun getString(key: String): String?
    fun getInt(key: String): Int
    fun getLong(key: String): Long
    fun getBoolean(key: String): Boolean

    fun putString(key: String, data: String)
    fun putInt(key: String, data: Int)
    fun putLong(key: String, data: Long)
    fun putBoolean(key: String, data: Boolean)

    fun clearData()

    suspend fun getCacheContents(type: String): Result<ContentEntity>
    suspend fun getContentQueries(type: String): Result<List<String>>
    suspend fun getLocalContents(type: String, query: String): Result<ContentEntity>
    suspend fun saveContents(type: String, query: String, response: Result<Content>): Result<Unit>

    /*   String between List parsing   */
//    fun getListData(key: String, onSuccess: (list: List<VipData>) -> Unit)
//    fun saveListData(key: String, list: List<VipData>)
}