package com.sungjae.portfolio.remote

import com.sungjae.portfolio.data.RemoteDataSource
import com.sungjae.portfolio.data.models.Content
import com.sungjae.portfolio.domain.entity.request.ContentEntity
import com.sungjae.portfolio.remote.component.Api
import com.sungjae.portfolio.remote.models.mapToContentData
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class RemoteDataSourceImpl(private val api: Api) : RemoteDataSource {
    override fun getRemoteContents(type: String, query: String): Single<Content> =
        api.getContentInfo(type, query)
            .map { response ->
                response.query = query
                response.mapToContentData()
            }.subscribeOn(Schedulers.io())
}