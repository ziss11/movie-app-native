package com.ziss.core.data.datasources.local.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MovieWithGenreEntity(
    @Embedded
    val movie: MovieEntity,

    @Relation(
        parentColumn = "id",
        entity = GenreEntity::class,
        entityColumn = "id",
        associateBy = Junction(
            value = GenreMovieCrossRef::class,
            parentColumn = "mId",
            entityColumn = "gId",
        )
    )
    val genres: List<GenreEntity>
)
