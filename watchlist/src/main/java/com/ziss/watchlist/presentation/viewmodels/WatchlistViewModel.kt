package com.ziss.watchlist.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.ziss.core.domain.usecases.MovieUseCase
import com.ziss.core.utils.DataMapper
import com.ziss.core.utils.toModelLiveData
import javax.inject.Inject

class WatchlistViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getWatchlistMovies() = movieUseCase.getWatchlistMovies().toModelLiveData { movies ->
        movies.map { DataMapper.toMovieModel(it) }
    }
}