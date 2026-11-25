package com.example.filmes_favoritos.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Filme::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmeDao(): FilmeDao
}
