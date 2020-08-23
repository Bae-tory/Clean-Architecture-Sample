package com.sungjae.portfolio.remote.di

import com.google.gson.GsonBuilder
import com.sungjae.portfolio.remote.BuildConfig
import com.sungjae.portfolio.remote.component.Api
import com.sungjae.portfolio.remote.component.Constants.CALL_TIME_OUT
import com.sungjae.portfolio.remote.component.Constants.CONNECT_TIME_OUT
import com.sungjae.portfolio.remote.component.Constants.READ_TIME_OUT
import com.sungjae.portfolio.remote.component.Constants.WRITE_TIME_OUT
import com.sungjae.portfolio.remote.component.Constants.cacheSize
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val userAgent = "User-Agent"
private const val Header = ""

val networkModule = module {

    single { Cache(androidApplication().cacheDir, cacheSize) }

    factory<Interceptor> {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    factory { (chain: Interceptor.Chain) ->
        val request =
            chain.request()
                .newBuilder()
                .addHeader(userAgent, Header)
                .build()

        chain.proceed(request)
    }
    single<CallAdapter.Factory> {
        RxJava2CallAdapterFactory.create()
    }

    single<Converter.Factory> {
        val gsonBuilder = GsonBuilder()
            .setPrettyPrinting()
            .setVersion(1.0)
            .create()
        GsonConverterFactory.create(gsonBuilder)
    }

    factory {
        OkHttpClient.Builder()
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
            .callTimeout(CALL_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(get<Interceptor>())
            .addInterceptor { get { parametersOf(it) } }
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .addConverterFactory(get())
            .addCallAdapterFactory(get())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    single {
        get<Retrofit>().create(Api::class.java)
    }
}