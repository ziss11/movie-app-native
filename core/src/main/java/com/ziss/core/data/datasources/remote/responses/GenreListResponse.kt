package com.ziss.core.data.datasources.remote.responses

import com.google.gson.annotations.SerializedName

data class GenreListResponse(
    @field: SerializedName("genres")
    val genres: List<GenreResponse>
)
