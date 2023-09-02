package com.ziss.core.data.datasources.remote.network

import com.ziss.core.data.datasources.remote.responses.MovieListResponse
import retrofit2.http.GET

interface ApiService {
    @GET("top_rated")
    suspend fun getTopRatedMovies(): MovieListResponse
}