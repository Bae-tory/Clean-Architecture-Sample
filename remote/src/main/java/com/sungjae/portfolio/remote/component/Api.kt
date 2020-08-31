package com.sungjae.portfolio.remote.component

import com.sungjae.portfolio.remote.models.ContentResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("v1/search/{type}.json")
    suspend fun getContentInfo(
        @Path("type")
        type: String,
        @Query("query")
        query: String
    ): ContentResponse
}