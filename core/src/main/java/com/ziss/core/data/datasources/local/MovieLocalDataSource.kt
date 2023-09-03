package com.ziss.core.data.datasources.local

import com.ziss.core.data.datasources.local.entities.MovieEntity
import com.ziss.core.data.datasources.local.entities.TypeEntity
import com.ziss.core.data.datasources.local.room.MovieDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieLocalDataSource @Inject constructor(private val movieDao: MovieDao) {
    fun getTopRatedMovies() = movieDao.getTopRatedMovies()
    fun getNowPlayingMovies() = movieDao.getNowPlayingMovies()

    suspend fun insertTopRatedMovies(movies: List<MovieEntity>) =
        movieDao.insertTopRatedMovies(movies)

    suspend fun insertNowPlayingMovies(movies: List<MovieEntity>) =
        movieDao.insertNowPlayingMovies(movies)

    suspend fun insertMovieType(movieType: TypeEntity) =
        movieDao.insertMovieType(movieType)
}