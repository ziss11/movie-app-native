package com.ziss.core.domain.entities

data class Movie(
    val id: Int = 0,
    val overview: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val video: Boolean = false,
    val title: String? = null,
    val posterPath: String? = null,
    val backdropPath: String?,
    val releaseDate: String? = null,
    val popularity: Number = 0,
    val voteAverage: Number = 0,
    val adult: Boolean = false,
    val voteCount: Int = 0,
)