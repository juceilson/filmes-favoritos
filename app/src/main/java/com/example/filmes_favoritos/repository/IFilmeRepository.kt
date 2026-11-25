package com.example.filmes_favoritos.repository

import com.example.filmes_favoritos.database.Filme
import kotlinx.coroutines.flow.Flow

interface IFilmeRepository {
    suspend fun searchMovies(query: String): List<Filme>
    suspend fun getMovieDetails(id: String): Filme?
    fun getFavoriteMovies(): Flow<List<Filme>>
    fun isFavorite(id: String): Flow<Boolean>
    suspend fun addFavorite(filme: Filme)
    suspend fun removeFavorite(id: String)
}