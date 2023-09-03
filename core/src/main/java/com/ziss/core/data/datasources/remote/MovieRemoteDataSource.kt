package com.ziss.core.data.datasources.remote

import com.ziss.core.data.datasources.remote.network.ApiService
import com.ziss.core.utils.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getTopRatedMovies() = flow {
        try {
            val result = apiService.getTopRatedMovies()

            if (result.results.isEmpty()) {
                emit(ApiState.Empty)
            } else {
                emit(ApiState.Success(result.results))
            }
        } catch (e: Exception) {
            emit(ApiState.Failed(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getNowPlayingMovies() = flow {
        try {
            val result = apiService.getNowPlayingMovies()

            if (result.results.isEmpty()) {
                emit(ApiState.Empty)
            } else {
                emit(ApiState.Success(result.results))
            }
        } catch (e: Exception) {
            emit(ApiState.Failed(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    fun getMoviesGenre() = flow {
        try {
            val result = apiService.getMoviesGenre()

            if (result.genres.isEmpty()) {
                emit(ApiState.Empty)
            } else {
                emit(ApiState.Success(result.genres))
            }
        } catch (e: Exception) {
            emit(ApiState.Failed(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}