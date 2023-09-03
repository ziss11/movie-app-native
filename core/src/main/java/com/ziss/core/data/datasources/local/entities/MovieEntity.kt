package com.ziss.core.data.datasources.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int,

    @ColumnInfo("typeId")
    val typeId: Int? = null,

    @ColumnInfo("isWatchlist")
    var isWatchlist: Boolean = false,

    @ColumnInfo("overview")
    val overview: String,

    @ColumnInfo("originalLanguage")
    val originalLanguage: String,

    @ColumnInfo("originalTitle")
    val originalTitle: String,

    @ColumnInfo("video")
    val video: Boolean,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("posterPath")
    val posterPath: String,

    @ColumnInfo("backdropPath")
    val backdropPath: String,

    @ColumnInfo("releaseDate")
    val releaseDate: String,

    @ColumnInfo("popularity")
    val popularity: Double,

    @ColumnInfo("voteAverage")
    val voteAverage: Double,

    @ColumnInfo("adult")
    val adult: Boolean,

    @ColumnInfo("voteCount")
    val voteCount: Int,
)
