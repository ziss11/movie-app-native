package com.ziss.core.domain.repositories

import com.ziss.core.domain.entities.Movie
import com.ziss.core.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getTopRatedMovies(): Flow<ResultState<List<Movie>>>

    fun getNowPlayingMovies(): Flow<ResultState<List<Movie>>>

    fun getWatchlistMovies(): Flow<List<Movie>>

    fun getWatchlistStatus(id: Int): Flow<Boolean>

    fun searchMovies(query: String): Flow<ResultState<List<Movie>>>

    suspend fun addWatchlistMovie(movie: Movie)

    suspend fun removeWatchlistMovie(id: Int)

}