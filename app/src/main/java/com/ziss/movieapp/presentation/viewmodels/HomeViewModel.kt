package com.ziss.movieapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ziss.core.domain.usecases.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getTopRatedMovies() = movieUseCase.getTopRatedMovies().asLiveData()
    fun getNowPlayingMovies() = movieUseCase.getNowPlayingMovies().asLiveData()
}