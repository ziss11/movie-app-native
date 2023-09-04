package com.ziss.movieapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ziss.core.domain.usecases.MovieUseCase
import com.ziss.core.utils.DataMapper
import com.ziss.core.utils.mapResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getTopRatedMovies() = movieUseCase.getTopRatedMovies().mapResult { movies ->
        movies.map { DataMapper.toMovieModel(it) }
    }.asLiveData()

    fun getNowPlayingMovies() = movieUseCase.getNowPlayingMovies().mapResult { movies ->
        movies.map { DataMapper.toMovieModel(it) }
    }.asLiveData()
}