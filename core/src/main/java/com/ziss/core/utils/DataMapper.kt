package com.ziss.core.utils

import com.ziss.core.data.datasources.local.entities.MovieEntity
import com.ziss.core.data.datasources.remote.responses.MovieResponse
import com.ziss.core.domain.entities.Movie
import com.ziss.core.presentation.models.MovieModel

object DataMapper {
    fun toMovieEntity(movie: Movie) = MovieEntity(
        id = movie.id,
        overview = movie.overview,
        originalLanguage = movie.originalLanguage,
        originalTitle = movie.originalTitle,
        video = movie.video,
        title = movie.title,
        posterPath = movie.posterPath,
        backdropPath = movie.backdropPath,
        releaseDate = movie.releaseDate,
        popularity = movie.popularity.toDouble(),
        voteAverage = movie.voteAverage.toDouble(),
        adult = movie.adult,
        voteCount = movie.voteCount,
    )

    fun toMovie(movie: MovieResponse) = Movie(
        id = movie.id,
        overview = movie.overview,
        originalLanguage = movie.originalLanguage,
        originalTitle = movie.originalTitle,
        video = movie.video,
        title = movie.title,
        posterPath = movie.posterPath,
        backdropPath = movie.backdropPath,
        releaseDate = movie.releaseDate,
        popularity = movie.popularity,
        voteAverage = movie.voteAverage,
        adult = movie.adult,
        voteCount = movie.voteCount,
    )

    fun toMovie(movie: MovieModel) = Movie(
        id = movie.id,
        overview = movie.overview,
        originalLanguage = movie.originalLanguage,
        originalTitle = movie.originalTitle,
        video = movie.video,
        title = movie.title,
        posterPath = movie.posterPath,
        backdropPath = movie.backdropPath,
        releaseDate = movie.releaseDate,
        popularity = movie.popularity,
        voteAverage = movie.voteAverage,
        adult = movie.adult,
        voteCount = movie.voteCount,
    )

    fun toMovie(movie: MovieEntity) = Movie(
        id = movie.id,
        overview = movie.overview,
        originalLanguage = movie.originalLanguage,
        originalTitle = movie.originalTitle,
        video = movie.video,
        title = movie.title,
        posterPath = movie.posterPath,
        backdropPath = movie.backdropPath,
        releaseDate = movie.releaseDate,
        popularity = movie.popularity,
        voteAverage = movie.voteAverage,
        adult = movie.adult,
        voteCount = movie.voteCount,
    )

    fun toMovieModel(movie: Movie) = MovieModel(
        id = movie.id,
        overview = movie.overview,
        originalLanguage = movie.originalLanguage,
        originalTitle = movie.originalTitle,
        video = movie.video,
        title = movie.title,
        posterPath = movie.posterPath,
        backdropPath = movie.backdropPath,
        releaseDate = movie.releaseDate,
        popularity = movie.popularity,
        voteAverage = movie.voteAverage,
        adult = movie.adult,
        voteCount = movie.voteCount,
    )
}
