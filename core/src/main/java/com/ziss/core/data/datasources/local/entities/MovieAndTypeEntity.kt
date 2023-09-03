package com.ziss.core.data.datasources.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class MovieAndTypeEntity(
    @Embedded
    val movie: MovieEntity,

    @Relation(
        parentColumn = "typeId",
        entityColumn = "id"
    )
    val type: TypeEntity? = null
)