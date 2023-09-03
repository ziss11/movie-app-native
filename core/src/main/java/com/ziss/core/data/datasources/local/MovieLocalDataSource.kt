package com.ziss.core.data.datasources.local

import com.ziss.core.data.datasources.local.entities.GenreEntity
import com.ziss.core.data.datasources.local.entities.GenreMovieCrossRef
import com.ziss.core.data.datasources.local.entities.MovieEntity
import com.ziss.core.data.datasources.local.entities.TypeEntity
import com.ziss.core.data.datasources.local.room.MovieDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieLocalDataSource @Inject constructor(private val movieDao: MovieDao) {
    fun getTopRatedMovies() = movieDao.getMovies(0)
    fun getNowPlayingMovies() = movieDao.getMovies(1)
    fun getMoviesGenre() = movieDao.getMoviesGenre()

    suspend fun insertTopRatedMovies(movies: List<MovieEntity>) =
        movieDao.insertTopRatedMovies(movies)

    suspend fun insertNowPlayingMovies(movies: List<MovieEntity>) =
        movieDao.insertNowPlayingMovies(movies)

    suspend fun insertMoviesGenre(genres: List<GenreEntity>) =
        movieDao.insertMoviesGenre(genres)

    suspend fun insertGenreMovieCrossRef(genreMovieCrossRef: List<GenreMovieCrossRef>) =
        movieDao.insertGenreMovieCrossRef(genreMovieCrossRef)

    suspend fun insertMovieType(movieType: TypeEntity) =
        movieDao.insertMovieType(movieType)
}