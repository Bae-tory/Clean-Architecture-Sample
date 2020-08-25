package com.sungjae.portfolio.local.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun <T> convertToJson(gsonData: Collection<T>): String {
    return Gson().toJson(gsonData)
}

inline fun <reified T> convertToGson(jsonData: String?): Collection<T> {
    val collectionType = TypeToken.getParameterized(Collection::class.java, T::class.java).type
    return Gson().fromJson(jsonData, collectionType)
}