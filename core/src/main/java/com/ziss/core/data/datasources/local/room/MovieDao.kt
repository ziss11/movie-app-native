package com.ziss.core.data.datasources.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.ziss.core.data.datasources.local.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Transaction
    @Query("SELECT * FROM movie")
    fun getWatchlistMovies(): Flow<List<MovieEntity>>

    @Query("SELECT EXISTS(SELECT * FROM movie WHERE id=:id)")
    fun isWatchlist(id: Int): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovie(movieEntity: MovieEntity)

    @Query("DELETE from movie WHERE id=:id")
    suspend fun removeMovie(id: Int)
}