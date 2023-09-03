package com.ziss.core.domain.usecases

import com.ziss.core.data.repositories.MovieRepository
import com.ziss.core.domain.entities.Movie
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: MovieRepository) :
    MovieUseCase {
    override fun getTopRatedMovies() = movieRepository.getTopRatedMovies()
    override fun getNowPlayingMovies() = movieRepository.getNowPlayingMovies()
    override fun getMoviesGenre() = movieRepository.getMoviesGenre()
    override fun getWatchlistMovies() = movieRepository.getWatchlistMovies()
    override fun setWatchlistMovie(movie: Movie, isWatchlist: Boolean) =
        movieRepository.setWatchlistMovie(movie, isWatchlist)
}