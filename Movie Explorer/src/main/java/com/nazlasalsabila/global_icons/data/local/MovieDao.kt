package com.nazlasalsabila.global_icons.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Insert(
        onConflict =
            OnConflictStrategy.REPLACE
    )
    suspend fun insertMovie(
        movie: MovieEntity
    )

    @Insert(
        onConflict =
            OnConflictStrategy.REPLACE
    )
    suspend fun insertFavorite(
        favorite: FavoriteEntity
    )

    @Query(
        "SELECT * FROM favorite"
    )
    suspend fun getFavorites():
            List<FavoriteEntity>

    @Delete
    suspend fun deleteFavorite(
        favorite: FavoriteEntity
    )
}