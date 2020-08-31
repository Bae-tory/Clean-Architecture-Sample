package com.sungjae.portfolio.data

import com.sungjae.portfolio.data.models.Content
import com.sungjae.portfolio.domain.exception.Result


interface RemoteDataSource {
    suspend fun getRemoteContents(type: String, query: String): Result<Content>
}