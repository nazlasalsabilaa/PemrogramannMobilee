package com.nazlasalsabila.global_icons.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")

data class MovieEntity(

    @PrimaryKey
    val movieId: Int,

    val title: String,

    val poster: String,

    val rating: Double
)