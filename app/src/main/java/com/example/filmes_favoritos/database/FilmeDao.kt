package com.example.filmes_favoritos.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmeDao {

    @Query("SELECT * FROM filmes_favoritos ORDER BY Title ASC")
    fun getAllFavorites(): Flow<List<Filme>>

    @Query("SELECT * FROM filmes_favoritos WHERE id = :id")
    fun getFavoriteById(id: String): Flow<Filme?>

    @Query("SELECT EXISTS(SELECT 1 FROM filmes_favoritos WHERE id = :id)")
    fun isFavorite(id: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(filme: Filme)

    @Delete
    suspend fun deleteFavorite(filme: Filme)

    @Query("DELETE FROM filmes_favoritos WHERE id = :id")
    suspend fun deleteFavoriteById(id: String)
}