package com.sungjae.portfolio.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sungjae.portfolio.domain.exception.Result
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface ContentDao {

    @Query("SELECT * FROM contents WHERE (type LIKE :type) ORDER BY id DESC LIMIT 1")
    suspend fun getContentCache(type: String): ContentLocal

    @Query("SELECT `query` FROM contents WHERE (type LIKE :type) ORDER BY id DESC")
    suspend fun getContentQuery(type: String): List<String>

    @Query("SELECT * FROM contents WHERE (type LIKE :type) AND (`query` LIKE :query)")
    suspend fun getContents(type: String, query: String): ContentLocal

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContent(contentLocal: ContentLocal)

}