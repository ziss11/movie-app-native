package com.ziss.movieapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ziss.core.domain.usecases.MovieUseCase
import com.ziss.core.presentation.models.MovieModel
import com.ziss.core.utils.DataMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getWatchlistStatus(id: Int) = movieUseCase.getWatchlistStatus(id).asLiveData()
    
    fun addWatchlistMovie(movie: MovieModel) {
        viewModelScope.launch {
            movieUseCase.addWatchlistMovie(DataMapper.toMovie(movie))
        }
    }

    fun removeWatchlistMovie(id: Int) {
        viewModelScope.launch {
            movieUseCase.removeWatchlistMovie(id)
        }
    }
}