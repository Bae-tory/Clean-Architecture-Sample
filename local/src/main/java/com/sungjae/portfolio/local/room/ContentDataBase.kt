package com.sungjae.portfolio.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ContentLocal::class], version = 1, exportSchema = false)
@TypeConverters(ContentTypeConvertor::class)
abstract class ContentDataBase : RoomDatabase() {

    abstract fun contentDao(): ContentDao

    companion object {
        const val DB_NAME = "Contents.db"
    }
}