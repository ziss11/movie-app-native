package com.ziss.core.data.datasources.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["mId", "gId"])
data class GenreMovieCrossRef(
    val mId: Int,
    @ColumnInfo(index = true)
    val gId: Int,
)
