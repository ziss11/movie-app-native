package com.ziss.core.data.datasources.remote.responses

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @field:SerializedName("results")
    val results: List<MovieResponse>
)