package com.ziss.core.data.datasources.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.ziss.core.data.datasources.local.entities.MovieAndTypeEntity
import com.ziss.core.data.datasources.local.entities.MovieEntity
import com.ziss.core.data.datasources.local.entities.TypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Transaction
    @Query("SELECT * FROM movie WHERE typeId=0")
    fun getTopRatedMovies(): Flow<List<MovieAndTypeEntity>>

    @Transaction
    @Query("SELECT * FROM movie WHERE typeId=1")
    fun getNowPlayingMovies(): Flow<List<MovieAndTypeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRatedMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNowPlayingMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieType(type: TypeEntity)
}