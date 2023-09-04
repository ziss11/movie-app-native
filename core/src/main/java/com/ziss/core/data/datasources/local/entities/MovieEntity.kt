package com.ziss.core.data.datasources.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int,

    @ColumnInfo("overview")
    val overview: String? = null,

    @ColumnInfo("originalLanguage")
    val originalLanguage: String? = null,

    @ColumnInfo("originalTitle")
    val originalTitle: String? = null,

    @ColumnInfo("video")
    val video: Boolean,

    @ColumnInfo("title")
    val title: String? = null,

    @ColumnInfo("posterPath")
    val posterPath: String? = null,

    @ColumnInfo("backdropPath")
    val backdropPath: String? = null,

    @ColumnInfo("releaseDate")
    val releaseDate: String? = null,

    @ColumnInfo("popularity")
    val popularity: Double = 0.0,

    @ColumnInfo("voteAverage")
    val voteAverage: Double = 0.0,

    @ColumnInfo("adult")
    val adult: Boolean = false,

    @ColumnInfo("voteCount")
    val voteCount: Int = 0,
)
