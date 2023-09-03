package com.ziss.movieapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.ziss.core.domain.usecases.MovieUseCase
import com.ziss.core.utils.toGenreModel
import com.ziss.core.utils.toModelLiveData
import com.ziss.core.utils.toMovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getMoviesGenre() = movieUseCase.getMoviesGenre().toModelLiveData { it.toGenreModel() }

    fun getTopRatedMovies() = movieUseCase.getTopRatedMovies().toModelLiveData { it.toMovieModel() }

    fun getNowPlayingMovies() =
        movieUseCase.getNowPlayingMovies().toModelLiveData { it.toMovieModel() }
}