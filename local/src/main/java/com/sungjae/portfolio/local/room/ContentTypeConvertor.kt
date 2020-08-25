package com.sungjae.portfolio.local.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sungjae.portfolio.data.models.ContentItem

class ContentTypeConvertor {

    private val gson = Gson()

    @TypeConverter
    fun toList(data: String?): List<ContentItem> = when (data) {
        null -> emptyList()
        else -> gson.fromJson<List<ContentItem>>(
            data,
            object : TypeToken<List<ContentItem>>() {}.type
        )
    }

    @TypeConverter
    fun toString(contents: List<ContentItem>): String {
        return gson.toJson(contents)
    }
}