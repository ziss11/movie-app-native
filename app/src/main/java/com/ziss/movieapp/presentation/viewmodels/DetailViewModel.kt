package com.ziss.movieapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ziss.core.domain.usecases.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getWatchlistStatus(id: Int) = movieUseCase.getWatchlistStatus(id).asLiveData()
    fun setWatchlistMovie(id: Int, isWatchlist: Int) {
        viewModelScope.launch {
            movieUseCase.setWatchlistMovie(id, isWatchlist)
        }
    }
}