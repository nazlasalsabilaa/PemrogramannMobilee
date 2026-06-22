package com.nazlasalsabila.global_icons.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nazlasalsabila.global_icons.GlobalIcon
import com.nazlasalsabila.global_icons.GlobalIconDao

@Database(
    entities = [
        MovieEntity::class,
        FavoriteEntity::class,
        GlobalIcon::class
    ],
    version = 4
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun globalIconDao(): GlobalIconDao
}