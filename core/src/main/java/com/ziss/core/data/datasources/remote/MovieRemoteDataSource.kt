package com.ziss.core.data.datasources.remote

import android.util.Log
import com.ziss.core.data.datasources.remote.network.ApiService
import com.ziss.core.utils.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getTopRateMovies() = flow {
        try {
            val result = apiService.getTopRatedMovies()

            if (result.results.isEmpty()) {
                emit(ApiState.Empty)
            } else {
                emit(ApiState.Success(result.results))
            }
        } catch (e: Exception) {
            Log.d("MovieResults", e.message.toString())
            emit(ApiState.Failed(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}