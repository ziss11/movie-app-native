package com.ziss.movieapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ziss.core.domain.usecases.MovieUseCase
import com.ziss.core.utils.DataMapper
import com.ziss.core.utils.mapResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@HiltViewModel
class SearchViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {
    val query = MutableStateFlow("")
    val searchResult = query
        .debounce(300)
        .distinctUntilChanged()
        .filter { it.trim().isNotEmpty() }
        .flatMapConcat { query ->
            movieUseCase.searchMovies(query).mapResult { movies ->
                movies.map {
                    DataMapper.toMovieModel(it)
                }
            }
        }
        .asLiveData()
}