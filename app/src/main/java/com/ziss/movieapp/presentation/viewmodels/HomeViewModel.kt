package com.ziss.movieapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.ziss.core.domain.usecases.MovieUseCase
import com.ziss.core.utils.DataMapper
import com.ziss.core.utils.toModelResultLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getMoviesGenre() = movieUseCase.getMoviesGenre().toModelResultLiveData { genres ->
        genres.map { DataMapper.toGenreModel(it) }
    }

    fun getTopRatedMovies() = movieUseCase.getTopRatedMovies().toModelResultLiveData { movies ->
        movies.map { DataMapper.toMovieModel(it) }
    }

    fun getNowPlayingMovies() = movieUseCase.getNowPlayingMovies().toModelResultLiveData { movies ->
        movies.map { DataMapper.toMovieModel(it) }
    }
}