package com.ziss.core.data.datasources.local

import com.ziss.core.data.datasources.local.entities.MovieEntity
import com.ziss.core.data.datasources.local.room.MovieDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieLocalDataSource @Inject constructor(private val movieDao: MovieDao) {
    fun getWatchlistMovies() = movieDao.getWatchlistMovies()

    fun isWatchlist(id: Int) = movieDao.isWatchlist(id)

    suspend fun addMovie(movie: MovieEntity) = movieDao.addMovie(movie)
    
    suspend fun removeMovie(id: Int) = movieDao.removeMovie(id)
}