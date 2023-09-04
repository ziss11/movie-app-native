package com.ziss.core.domain.usecases

import com.ziss.core.data.repositories.MovieRepository
import com.ziss.core.domain.entities.Movie
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: MovieRepository) :
    MovieUseCase {
    override fun getTopRatedMovies() = movieRepository.getTopRatedMovies()

    override fun getNowPlayingMovies() = movieRepository.getNowPlayingMovies()

    override fun searchMovies(query: String) = movieRepository.searchMovies(query)

    override fun getWatchlistMovies() = movieRepository.getWatchlistMovies()

    override fun getWatchlistStatus(id: Int) = movieRepository.getWatchlistStatus(id)

    override suspend fun addWatchlistMovie(movie: Movie) = movieRepository.addWatchlistMovie(movie)

    override suspend fun removeWatchlistMovie(id: Int) = movieRepository.removeWatchlistMovie(id)
}