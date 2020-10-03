package com.sungjae.portfolio.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContentDao {

    @Query("SELECT * FROM content WHERE (type LIKE :type) ORDER BY id DESC LIMIT 1")
    suspend fun getContentCache(type: String): RoomDataEntity.Content

    @Query("SELECT `query` FROM content WHERE (type LIKE :type) ORDER BY id DESC")
    suspend fun getContentQuery(type: String): List<String>

    @Query("SELECT * FROM content WHERE (type LIKE :type) AND (`query` LIKE :query)")
    suspend fun getContents(type: String, query: String): RoomDataEntity.Content

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContent(contentLocal: RoomDataEntity.Content)

}