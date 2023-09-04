package com.ziss.core.data.datasources.remote.network

import com.ziss.core.data.datasources.remote.responses.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): MovieListResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): MovieListResponse

    @GET("search/movie")
    suspend fun searchMovies(@Query("query") query: String): MovieListResponse
}