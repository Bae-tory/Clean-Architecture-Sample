package com.sungjae.portfolio.remote.component

import com.sungjae.portfolio.remote.models.Sample
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {
    @POST("m_Tablet/init")
    fun checkDevice(@Body request: Sample): Single<Sample>

}