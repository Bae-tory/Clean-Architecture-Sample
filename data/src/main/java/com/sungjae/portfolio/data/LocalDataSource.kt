package com.sungjae.portfolio.data

import com.sungjae.portfolio.data.models.Content
import com.sungjae.portfolio.domain.entity.request.ContentEntity
import io.reactivex.Completable
import io.reactivex.Single


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

    fun getCacheContents(type: String): Single<ContentEntity>
    fun getContentQueries(type: String): Single<List<String>>
    fun getLocalContents(type: String, query: String): Single<ContentEntity>
    fun saveContents(type: String, query: String, response: Content): Completable

    /*   String between List parsing   */
//    fun getListData(key: String, onSuccess: (list: List<VipData>) -> Unit)
//    fun saveListData(key: String, list: List<VipData>)
}