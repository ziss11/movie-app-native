package com.ziss.core.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.ziss.core.R
import com.ziss.core.data.datasources.local.entities.MovieEntity
import com.ziss.core.data.datasources.remote.responses.MovieResponse
import com.ziss.core.domain.entities.Movie

fun ImageView.loadImage(url: Any?) {
    Glide.with(this.context).load(url).centerCrop().placeholder(R.drawable.ic_broken_image_24)
        .error(R.drawable.ic_broken_image_24).into(this)
}

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
        popularity = it.popularity.toDouble(),
        voteAverage = it.voteAverage.toDouble(),
        adult = it.adult,
        voteCount = it.voteCount,
    )
}