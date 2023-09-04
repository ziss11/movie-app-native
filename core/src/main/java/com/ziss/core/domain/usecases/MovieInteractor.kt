package com.ziss.core.domain.usecases

import com.ziss.core.data.repositories.MovieRepository
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: MovieRepository) :
    MovieUseCase {
    override fun getTopRatedMovies() = movieRepository.getTopRatedMovies()
    override fun getNowPlayingMovies() = movieRepository.getNowPlayingMovies()
    override fun getMoviesGenre() = movieRepository.getMoviesGenre()
    override fun getWatchlistMovies() = movieRepository.getWatchlistMovies()
    override fun getWatchlistStatus(id: Int) = movieRepository.getWatchlistStatus(id)
    override suspend fun setWatchlistMovie(id: Int, isWatchlist: Int) =
        movieRepository.setWatchlistMovie(id, isWatchlist)
}