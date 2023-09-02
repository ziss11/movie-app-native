package com.ziss.core.domain.entities

data class Movie(
    val id: Int,
    val overview: String,
    val originalLanguage: String,
    val originalTitle: String,
    val video: Boolean,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val popularity: Int? = null,
    val voteAverage: Double? = null,
    val adult: Boolean,
    val voteCount: Int,
)