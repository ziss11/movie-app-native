package com.ziss.movieapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.ziss.core.domain.usecases.MovieUseCase
import com.ziss.core.utils.toGenreModelList
import com.ziss.core.utils.toModelLiveData
import com.ziss.core.utils.toMovieModelList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getMoviesGenre() = movieUseCase.getMoviesGenre().toModelLiveData { it.toGenreModelList() }

    fun getTopRatedMovies() =
        movieUseCase.getTopRatedMovies().toModelLiveData { it.toMovieModelList() }

    fun getNowPlayingMovies() =
        movieUseCase.getNowPlayingMovies().toModelLiveData { it.toMovieModelList() }
}