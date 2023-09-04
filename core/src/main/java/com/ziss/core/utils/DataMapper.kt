package com.ziss.core.utils

import com.ziss.core.data.datasources.local.entities.GenreEntity
import com.ziss.core.data.datasources.local.entities.MovieEntity
import com.ziss.core.data.datasources.local.entities.MovieWithGenreEntity
import com.ziss.core.data.datasources.remote.responses.GenreResponse
import com.ziss.core.data.datasources.remote.responses.MovieResponse
import com.ziss.core.domain.entities.Genre
import com.ziss.core.domain.entities.Movie
import com.ziss.core.presentation.models.GenreModel
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

    fun toMovieEntity(movie: MovieResponse, typeId: Int) = MovieEntity(
        id = movie.id,
        typeId = typeId,
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

    fun toMovie(movie: MovieWithGenreEntity) = Movie(
        id = movie.movie.id,
        overview = movie.movie.overview,
        originalLanguage = movie.movie.originalLanguage,
        originalTitle = movie.movie.originalTitle,
        video = movie.movie.video,
        genres = movie.genres.map { toGenre(it) },
        title = movie.movie.title,
        posterPath = movie.movie.posterPath,
        backdropPath = movie.movie.backdropPath,
        releaseDate = movie.movie.releaseDate,
        popularity = movie.movie.popularity,
        voteAverage = movie.movie.voteAverage,
        adult = movie.movie.adult,
        voteCount = movie.movie.voteCount,
    )

    fun toMovie(movie: MovieModel) = Movie(
        id = movie.id,
        overview = movie.overview,
        originalLanguage = movie.originalLanguage,
        originalTitle = movie.originalTitle,
        video = movie.video,
        genres = movie.genres.map { toGenre(it) },
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
        genres = movie.genres.map { toGenreModel(it) },
        title = movie.title,
        posterPath = movie.posterPath,
        backdropPath = movie.backdropPath,
        releaseDate = movie.releaseDate,
        popularity = movie.popularity,
        voteAverage = movie.voteAverage,
        adult = movie.adult,
        voteCount = movie.voteCount,
    )

    fun toGenre(genre: GenreModel) = Genre(
        id = genre.id, name = genre.name
    )

    fun toGenreModel(genre: Genre) = GenreModel(
        id = genre.id, name = genre.name
    )

    fun toGenre(genre: GenreEntity) = Genre(
        id = genre.id, name = genre.name
    )

    fun toGenreEntity(genre: GenreResponse) = GenreEntity(
        id = genre.id, name = genre.name
    )
}
