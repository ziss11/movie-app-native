package com.ziss.core.domain.usecases

import com.ziss.core.domain.entities.Movie
import com.ziss.core.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getTopRatedMovies(): Flow<ResultState<List<Movie>>>
    fun getNowPlayingMovies(): Flow<ResultState<List<Movie>>>
}