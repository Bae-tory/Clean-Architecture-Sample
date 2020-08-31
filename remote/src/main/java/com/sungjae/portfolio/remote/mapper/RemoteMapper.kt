package com.sungjae.portfolio.remote.mapper

import com.sungjae.portfolio.data.models.ContentItem

interface RemoteMapper<T, E> {

    fun T.toData(query: String): List<ContentItem>
}