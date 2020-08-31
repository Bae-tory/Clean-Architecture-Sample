package com.sungjae.portfolio.local

import android.content.SharedPreferences
import androidx.core.content.edit
import com.sungjae.portfolio.data.LocalDataSource
import com.sungjae.portfolio.data.models.Content
import com.sungjae.portfolio.domain.entity.request.ContentEntity
import com.sungjae.portfolio.domain.entity.request.ContentEntityItem
import com.sungjae.portfolio.domain.exception.Result
import com.sungjae.portfolio.local.extensions.convertToGson
import com.sungjae.portfolio.local.extensions.convertToJson
import com.sungjae.portfolio.local.mapper.ContentLocalMapper
import com.sungjae.portfolio.local.room.ContentDao
import com.sungjae.portfolio.local.room.ContentLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSourceImpl(
    private val pref: SharedPreferences,
    private val contentDao: ContentDao
) : LocalDataSource, ContentLocalMapper<ContentLocal, ContentEntity, Content> {

    override suspend fun getCacheContents(type: String): Result<ContentEntity> =
        withContext(Dispatchers.IO) {
            try {
                Result.OnSuccess(contentDao.getContentCache(type).toDomain())
            } catch (e: Exception) {
                Result.OnError(e)
            }
        }

    override suspend fun getContentQueries(type: String): Result<List<String>> =
        withContext(Dispatchers.IO) {
            try {
                Result.OnSuccess(contentDao.getContentQuery(type))
            } catch (e: Exception) {
                Result.OnError(e)
            }
        }

    override suspend fun getLocalContents(type: String, query: String): Result<ContentEntity> =
        withContext(Dispatchers.IO) {
            try {
                Result.OnSuccess(contentDao.getContents(type, query).toDomain())
            } catch (e: Exception) {
                Result.OnError(e)
            }
        }

    override suspend fun saveContents(type: String, query: String, response: Result<Content>): Result<Unit> =
        withContext(Dispatchers.IO) {
            try {
                when (response) {
                    is Result.OnSuccess -> {
                        Result.OnSuccess(contentDao.insertContent(response.data.fromData(type, query)))
                    }
                    is Result.OnError -> throw Exception("saveContents Error")
                }
            } catch (e: Exception) {
                Result.OnError(e)
            }
        }

    override fun getString(key: String) = pref.getString(key, null)
    override fun getInt(key: String) = pref.getInt(key, -1)
    override fun getLong(key: String) = pref.getLong(key, -1L)
    override fun getBoolean(key: String) = pref.getBoolean(key, false)
    override fun putString(key: String, data: String) {

        pref.edit {
            putString(key, data)
        }
    }

    override fun putInt(key: String, data: Int) {
        pref.edit {
            putInt(key, data)
        }
    }

    override fun putLong(key: String, data: Long) {
        pref.edit {
            putLong(key, data)
        }
    }

    override fun putBoolean(key: String, data: Boolean) {
        pref.edit {
            putBoolean(key, data)
        }
    }

    override fun clearData() {
        pref.edit {
            clear()
        }
    }

    override fun ContentLocal.toDomain(): ContentEntity =
        ContentEntity(
            this.query,
            convertToGson<ContentEntityItem>(convertToJson(this.list)).toList()
        )

    override fun Content.fromData(type: String, query: String): ContentLocal =
        ContentLocal(
            id = System.currentTimeMillis(),
            list = this.contentItems,
            type = type,
            query = query
        )
}
