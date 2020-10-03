package com.sungjae.portfolio.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sungjae.portfolio.data.models.ContentItem

sealed class RoomDataEntity {

    @Entity(tableName = "content")
    data class Content(
        @PrimaryKey val id: Long,
        val list: List<ContentItem>,
        val type: String,
        val query: String
    ) {
        companion object {
            fun empty(type: String, query: String): Content =
                Content(System.currentTimeMillis(), emptyList(), type, query)
        }
    }
}
