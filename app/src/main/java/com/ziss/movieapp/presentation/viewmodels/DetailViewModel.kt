package com.ziss.movieapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.ziss.core.domain.usecases.MovieUseCase
import com.ziss.core.presentation.models.MovieModel
import com.ziss.core.utils.DataMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setWatchlistMovie(movie: MovieModel, isWatchlist: Boolean) {
        movieUseCase.setWatchlistMovie(DataMapper.toMovie(movie), isWatchlist)
    }
}