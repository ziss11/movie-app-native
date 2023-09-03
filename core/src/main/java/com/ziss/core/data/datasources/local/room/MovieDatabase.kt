package com.ziss.core.data.datasources.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ziss.core.data.datasources.local.entities.MovieEntity
import com.ziss.core.data.datasources.local.entities.TypeEntity

@Database(
    entities = [MovieEntity::class, TypeEntity::class],
    version = 2,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}