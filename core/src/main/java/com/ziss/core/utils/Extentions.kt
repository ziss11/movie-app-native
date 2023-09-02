package com.ziss.core.utils

import com.ziss.core.data.datasources.local.entities.MovieEntity
import com.ziss.core.data.datasources.remote.responses.MovieResponse
import com.ziss.core.domain.entities.Movie

fun List<MovieEntity>.toMovieList() = this.map {
    Movie(
        id = it.id,
        overview = it.overview,
        originalLanguage = it.originalLanguage,
        originalTitle = it.originalTitle,
        video = it.video,
        title = it.title,
        posterPath = it.posterPath,
        backdropPath = it.backdropPath,
        releaseDate = it.releaseDate,
        popularity = it.popularity,
        voteAverage = it.voteAverage,
        adult = it.adult,
        voteCount = it.voteCount,
    )
}

fun List<MovieResponse>.toMovieEntityList() = this.map {
    MovieEntity(
        id = it.id,
        overview = it.overview,
        originalLanguage = it.originalLanguage,
        originalTitle = it.originalTitle,
        video = it.video,
        title = it.title,
        posterPath = it.posterPath,
        backdropPath = it.backdropPath,
        releaseDate = it.releaseDate,
        popularity = it.popularity,
        voteAverage = it.voteAverage,
        adult = it.adult,
        voteCount = it.voteCount,
    )
}