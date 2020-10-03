package com.sungjae.portfolio.remote.component

import com.sungjae.portfolio.remote.models.ContentResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("v1/search/{type}.json")
    fun getContentInfo(
        @Path("type")
        type: String,
        @Query("query")
        query: String
    ): Single<ContentResponse>
}