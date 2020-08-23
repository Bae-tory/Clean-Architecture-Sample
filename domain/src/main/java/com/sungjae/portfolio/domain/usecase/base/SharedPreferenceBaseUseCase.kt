package com.sungjae.portfolio.domain.usecase.base

abstract class SharedPreferenceBaseUseCase {

    abstract fun put(key: String, params: Any?)
    abstract fun getString(key: String): String?
    abstract fun getInt(key: String): Int
    abstract fun getBoolean(key: String): Boolean
    abstract fun getLong(key: String): Long
    abstract fun clearData()
}