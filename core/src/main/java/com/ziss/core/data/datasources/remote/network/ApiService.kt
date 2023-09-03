package com.ziss.core.data.datasources.remote.network

import com.ziss.core.data.datasources.remote.responses.GenreListResponse
import com.ziss.core.data.datasources.remote.responses.MovieListResponse
import retrofit2.http.GET

interface ApiService {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): MovieListResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): MovieListResponse

    @GET("genre/movie/list")
    suspend fun getMoviesGenre(): GenreListResponse
}