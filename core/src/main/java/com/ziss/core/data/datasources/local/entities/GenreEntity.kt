package com.ziss.core.data.datasources.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre")
data class GenreEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int,

    @ColumnInfo("name")
    val name: String,
)
