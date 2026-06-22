package com.nazlasalsabila.global_icons.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "favorite",

    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["movieId"],
            childColumns = ["movieId"],
            onDelete = ForeignKey.CASCADE
        )
    ],

    indices = [
        Index(
            value = ["movieId"],
            unique = true
        )
    ]
)

data class FavoriteEntity(

    @PrimaryKey(
        autoGenerate = true
    )
    val favoriteId: Int = 0,

    val movieId: Int,

    val title: String,

    val poster: String
)