package com.sungjae.portfolio.data

import com.sungjae.portfolio.data.models.toEntity
import com.sungjae.portfolio.domain.entity.request.ContentEntity
import com.sungjae.portfolio.domain.repository.Repository
import io.reactivex.Single

class RepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val contentDataSource: ContentDataSource
) : Repository {

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

    private fun loadRemoteContents(type: String, query: String): Single<ContentEntity> =
        remoteDataSource.getRemoteContents(type, query)
            .flatMap { response ->
                localDataSource.saveContents(type, query, response)
                    .toSingle { response.toEntity() }
            }

    override fun getContents(type: String, query: String): Single<ContentEntity> =
        loadRemoteContents(type, query)

    override fun getContentsByHistory(type: String, query: String): Single<ContentEntity> =
        localDataSource.getLocalContents(type, query)

    override fun getContentQueries(type: String): Single<List<String>> =
        localDataSource.getContentQueries(type)

    override fun getCache(type: String): Single<ContentEntity> =
        localDataSource.getCacheContents(type)
}