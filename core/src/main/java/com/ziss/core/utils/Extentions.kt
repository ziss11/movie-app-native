package com.ziss.core.utils

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.bumptech.glide.Glide
import com.ziss.core.R
import com.ziss.core.data.datasources.local.entities.GenreEntity
import com.ziss.core.data.datasources.local.entities.MovieEntity
import com.ziss.core.data.datasources.local.entities.MovieWithGenreEntity
import com.ziss.core.data.datasources.remote.responses.GenreResponse
import com.ziss.core.data.datasources.remote.responses.MovieResponse
import com.ziss.core.domain.entities.Genre
import com.ziss.core.domain.entities.Movie
import com.ziss.core.presentation.models.GenreModel
import com.ziss.core.presentation.models.MovieModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun ImageView.loadImage(url: Any?) {
    Glide.with(this.context).load(url).centerCrop().placeholder(R.drawable.ic_load_image)
        .error(R.drawable.ic_broken_image_24).into(this)
}

fun List<Genre>.toGenreModel() = this.map {
    GenreModel(id = it.id, name = it.name)
}

fun List<Movie>.toMovieModel() = this.map {
    MovieModel(
        id = it.id,
        overview = it.overview,
        originalLanguage = it.originalLanguage,
        originalTitle = it.originalTitle,
        video = it.video,
        genres = it.genres.toGenreModel(),
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

fun List<GenreEntity>.toGenreList() = this.map {
    Genre(id = it.id, name = it.name)
}

fun List<GenreResponse>.toGenreEntityList() = this.map {
    GenreEntity(id = it.id, name = it.name)
}

fun List<MovieWithGenreEntity>.toMovieList() = this.map {
    val movieAndType = it.movieAndType
    Movie(
        id = movieAndType.movie.id,
        overview = movieAndType.movie.overview,
        originalLanguage = movieAndType.movie.originalLanguage,
        originalTitle = movieAndType.movie.originalTitle,
        video = movieAndType.movie.video,
        genres = it.genres.toGenreList(),
        title = movieAndType.movie.title,
        posterPath = movieAndType.movie.posterPath,
        backdropPath = movieAndType.movie.backdropPath,
        releaseDate = movieAndType.movie.releaseDate,
        popularity = movieAndType.movie.popularity,
        voteAverage = movieAndType.movie.voteAverage,
        adult = movieAndType.movie.adult,
        voteCount = movieAndType.movie.voteCount,
    )
}

fun List<MovieResponse>.toMovieEntityList(typeId: Int) = this.map {
    MovieEntity(
        id = it.id,
        typeId = typeId,
        overview = it.overview,
        originalLanguage = it.originalLanguage,
        originalTitle = it.originalTitle,
        video = it.video,
        title = it.title,
        posterPath = it.posterPath,
        backdropPath = it.backdropPath,
        releaseDate = it.releaseDate,
        popularity = it.popularity.toDouble(),
        voteAverage = it.voteAverage.toDouble(),
        adult = it.adult,
        voteCount = it.voteCount,
    )
}

fun <T, R> Flow<ResultState<T>>.toModelLiveData(
    mapper: suspend (T) -> R
): LiveData<ResultState<R>> {
    return this.map { resultState ->
        when (resultState) {
            is ResultState.Success -> {
                val data = resultState.data!!.let { mapper(it) }
                ResultState.Success(data)
            }

            is ResultState.Failed -> {
                ResultState.Failed(resultState.message.toString())
            }

            is ResultState.Loading -> {
                ResultState.Loading()
            }
        }
    }.asLiveData()
}