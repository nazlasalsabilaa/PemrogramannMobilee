package com.nazlasalsabila.global_icons.data.local

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    private var INSTANCE:
            AppDatabase? =
        null

    fun getDatabase(
        context: Context
    ): AppDatabase {

        return INSTANCE
            ?: synchronized(
                this
            ) {

                Room
                    .databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "global_icon_db"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {

                        INSTANCE =
                            it
                    }
            }
    }
}