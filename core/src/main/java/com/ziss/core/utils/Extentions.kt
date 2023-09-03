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

fun Movie.toMovieEntity() = MovieEntity(
    id = this.id,
    overview = this.overview,
    originalLanguage = this.originalLanguage,
    originalTitle = this.originalTitle,
    video = this.video,
    title = this.title,
    posterPath = this.posterPath,
    backdropPath = this.backdropPath,
    releaseDate = this.releaseDate,
    popularity = this.popularity.toDouble(),
    voteAverage = this.voteAverage.toDouble(),
    adult = this.adult,
    voteCount = this.voteCount,
)

fun List<Genre>.toGenreModelList() = this.map {
    GenreModel(id = it.id, name = it.name)
}

fun List<Movie>.toMovieModelList() = this.map {
    MovieModel(
        id = it.id,
        overview = it.overview,
        originalLanguage = it.originalLanguage,
        originalTitle = it.originalTitle,
        video = it.video,
        genres = it.genres.toGenreModelList(),
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
    Movie(
        id = it.movie.id,
        overview = it.movie.overview,
        originalLanguage = it.movie.originalLanguage,
        originalTitle = it.movie.originalTitle,
        video = it.movie.video,
        genres = it.genres.toGenreList(),
        title = it.movie.title,
        posterPath = it.movie.posterPath,
        backdropPath = it.movie.backdropPath,
        releaseDate = it.movie.releaseDate,
        popularity = it.movie.popularity,
        voteAverage = it.movie.voteAverage,
        adult = it.movie.adult,
        voteCount = it.movie.voteCount,
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
                val data = mapper(resultState.data!!)
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