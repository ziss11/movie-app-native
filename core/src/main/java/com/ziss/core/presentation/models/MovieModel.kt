package com.ziss.core.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel(
    val id: Int,
    val overview: String,
    val originalLanguage: String,
    val originalTitle: String,
    val video: Boolean,
    val title: String,
    val genres: List<GenreModel>,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val popularity: Number,
    val voteAverage: Number,
    val adult: Boolean,
    val voteCount: Int,
) : Parcelable
