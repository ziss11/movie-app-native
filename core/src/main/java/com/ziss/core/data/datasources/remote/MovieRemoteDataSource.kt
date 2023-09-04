package com.ziss.core.data.datasources.remote

import android.util.Log
import com.ziss.core.data.datasources.remote.network.ApiService
import com.ziss.core.utils.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRemoteDataSource @Inject constructor(private val apiService: ApiService) {
    fun getTopRatedMovies() = flow {
        emit(ResultState.Loading)

        try {
            val result = apiService.getTopRatedMovies()
            emit(ResultState.Success(result.results))
        } catch (e: Exception) {
            emit(ResultState.Failed(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    fun getNowPlayingMovies() = flow {
        emit(ResultState.Loading)

        try {
            val result = apiService.getNowPlayingMovies()
            emit(ResultState.Success(result.results))
        } catch (e: Exception) {
            emit(ResultState.Failed(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    fun searchMovies(query: String) = flow {
        emit(ResultState.Loading)

        try {
            val movies = apiService.searchMovies(query)
            Log.d("SearchMovies", movies.toString())
            emit(ResultState.Success(movies.results))
        } catch (e: Exception) {
            emit(ResultState.Failed(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}