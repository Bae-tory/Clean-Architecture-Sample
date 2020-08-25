package com.sungjae.portfolio.data

import com.sungjae.portfolio.data.models.Content
import com.sungjae.portfolio.domain.entity.request.ContentEntity
import io.reactivex.Single


interface RemoteDataSource {
    fun getRemoteContents(type: String, query: String): Single<Content>
}