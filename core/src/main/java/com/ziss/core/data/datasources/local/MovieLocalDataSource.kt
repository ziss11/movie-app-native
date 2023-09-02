package com.ziss.core.data.datasources.local

import com.ziss.core.data.datasources.local.entities.MovieEntity
import com.ziss.core.data.datasources.local.room.MovieDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieLocalDataSource @Inject constructor(private val movieDao: MovieDao) {
    fun getTopRatedMovies() = movieDao.getTopRatedMovies()
    suspend fun insertTopRatedMovies(movies: List<MovieEntity>) =
        movieDao.insertTopRatedMovies(movies)
}