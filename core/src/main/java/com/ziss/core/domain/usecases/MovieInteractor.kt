package com.ziss.core.domain.usecases

import com.ziss.core.data.repositories.MovieRepository
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: MovieRepository) :
    MovieUseCase {
    override fun getTopRatedMovies() = movieRepository.getTopRatedMovies()
    override fun getNowPlayingMovies() = movieRepository.getNowPlayingMovies()
    override fun getMoviesGenre() = movieRepository.getMoviesGenre()
}