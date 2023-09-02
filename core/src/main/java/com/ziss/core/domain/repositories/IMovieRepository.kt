package com.ziss.core.domain.repositories

import com.ziss.core.domain.entities.Movie
import com.ziss.core.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getTopRatedMovies(): Flow<ResultState<List<Movie>>>
}