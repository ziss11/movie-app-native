package com.ziss.core.data.datasources.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ziss.core.data.datasources.local.entities.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}