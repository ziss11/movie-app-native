package com.ziss.core.data.datasources.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.ziss.core.data.datasources.local.entities.GenreEntity
import com.ziss.core.data.datasources.local.entities.GenreMovieCrossRef
import com.ziss.core.data.datasources.local.entities.MovieEntity
import com.ziss.core.data.datasources.local.entities.MovieWithGenreEntity
import com.ziss.core.data.datasources.local.entities.TypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Transaction
    @Query("SELECT * FROM movie WHERE typeId=:typeId")
    fun getMovies(typeId: Int): Flow<List<MovieWithGenreEntity>>

    @Query("SELECT * FROM genre")
    fun getMoviesGenre(): Flow<List<GenreEntity>>

    @Transaction
    @Query("SELECT * FROM movie WHERE isWatchlist=1")
    fun getWatchlistMovies(): Flow<List<MovieWithGenreEntity>>

    @Query("SELECT EXISTS(SELECT * FROM movie WHERE id=:id AND isWatchlist=1)")
    fun isWatchlist(id: Int): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRatedMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNowPlayingMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoviesGenre(genres: List<GenreEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenreMovieCrossRef(genreMovieCrossRef: List<GenreMovieCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieType(type: TypeEntity)

    @Query("UPDATE movie SET isWatchlist=:isWatchlist WHERE id=:id")
    suspend fun updateWatchlistMovie(id: Int, isWatchlist: Int)
}