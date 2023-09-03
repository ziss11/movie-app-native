package com.ziss.core.data.datasources.remote.responses

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @field: SerializedName("id")
    val id: Int,

    @field: SerializedName("name")
    val name: String
)
