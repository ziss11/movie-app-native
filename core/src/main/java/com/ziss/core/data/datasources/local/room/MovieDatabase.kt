package com.ziss.core.data.datasources.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ziss.core.data.datasources.local.entities.GenreEntity
import com.ziss.core.data.datasources.local.entities.GenreMovieCrossRef
import com.ziss.core.data.datasources.local.entities.MovieEntity
import com.ziss.core.data.datasources.local.entities.TypeEntity

@Database(
    entities = [MovieEntity::class, TypeEntity::class, GenreEntity::class, GenreMovieCrossRef::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}