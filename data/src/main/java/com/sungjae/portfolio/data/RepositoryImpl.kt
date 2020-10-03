package com.sungjae.portfolio.data

import android.util.Log
import com.sungjae.portfolio.data.models.Content
import com.sungjae.portfolio.data.models.ContentItem
import com.sungjae.portfolio.domain.entity.request.ContentEntity
import com.sungjae.portfolio.domain.entity.request.ContentEntityItem
import com.sungjae.portfolio.domain.exception.Result
import com.sungjae.portfolio.domain.repository.Repository
import com.sungjae.portfolio.mapper.ContentDataMapper
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val contentDataSource: ContentDataSource
) : Repository, ContentDataMapper<Content, ContentEntity> {

    override fun clearData() {
        localDataSource.clearData()
    }

    //Local
    override fun getString(key: String) = localDataSource.getString(key)
    override fun getInt(key: String) = localDataSource.getInt(key)
    override fun getLong(key: String) = localDataSource.getLong(key)
    override fun getBoolean(key: String) = localDataSource.getBoolean(key)
    override fun putString(key: String, data: String) {
        localDataSource.putString(key, data)
    }

    override fun putInt(key: String, data: Int) {
        localDataSource.putInt(key, data)
    }

    override fun putLong(key: String, data: Long) {
        val content = Content("", listOf(ContentItem()))
        content.toDomain()
        localDataSource.putLong(key, data)
    }

    override fun putBoolean(key: String, data: Boolean) {
        localDataSource.putBoolean(key, data)
    }

    //Content
    override fun getVersionName(): String = contentDataSource.getVersionName()
    override fun getVersionCode(): Int = contentDataSource.getVersionCode()
    override fun getDeviceId(): String? = contentDataSource.getDeviceId()
    override fun getSDKVersion(): String = contentDataSource.getSDKVersion()

    private suspend fun loadRemoteContents(type: String, query: String): Result<ContentEntity> {
        return when (val response = remoteDataSource.getRemoteContents(type, query)) {
            is Result.OnSuccess -> {
                Log.d("Result.OnSuccess", "$response")
                localDataSource.saveContents(type, query, response)
                Result.OnSuccess(response.data.toDomain())
            }
            is Result.OnError -> throw Exception("Error Remote Contents")
        }
    }

    override suspend fun getContents(type: String, query: String): Result<ContentEntity> =
        loadRemoteContents(type, query)

    override suspend fun getContentsByHistory(type: String, query: String): Result<ContentEntity> =
        localDataSource.getLocalContents(type, query)

    override suspend fun getContentQueries(type: String): Result<List<String>> =
        localDataSource.getContentQueries(type)

    override suspend fun getCache(type: String): Result<ContentEntity> =
        localDataSource.getCacheContents(type)

    override fun Content.toDomain(): ContentEntity =
        ContentEntity(
            query = this.query,
            contentEntityItems = this.contentItems.map {
                ContentEntityItem(
                    image = it.image,
                    actor = it.actor,
                    description = it.description,
                    title = it.title,
                    link = it.link
                )
            }
        )

    override fun ContentEntity.fromDomain(): Content =
        Content(
            query = this.query,
            contentItems = this.contentEntityItems.map {
                ContentItem(
                    image = it.image,
                    actor = it.actor,
                    description = it.description,
                    title = it.title,
                    link = it.link
                )
            }
        )

}