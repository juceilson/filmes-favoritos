package com.example.filmes_favoritos.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.filmes_favoritos.database.Filme
import com.example.filmes_favoritos.database.FilmeDao
import com.example.filmes_favoritos.service.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class FilmeRepository(
    private val apiService: ApiService,
    private val favoriteMovieDao: FilmeDao
) : IFilmeRepository {

    override suspend fun searchMovies(query: String): List<Filme> {
        return try {
            val response = apiService.searchMovies(query)
            response.search ?: emptyList()
        } catch (e: Exception) {
            Log.e(TAG, "Erro na rede ao buscar os filmes", e)
            emptyList()
        }
    }

    override suspend fun getMovieDetails(id: String): Filme? {
        return try {
            val favoriteMovie = favoriteMovieDao.getFavoriteById(id).firstOrNull()
            if (favoriteMovie != null) {
                return favoriteMovie
            }
            apiService.getMovieDetails(id)
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao buscar detalhes do filme", e)
            null
        }
    }

    override fun getFavoriteMovies(): Flow<List<Filme>> {
        return favoriteMovieDao.getAllFavorites()
    }

    override fun isFavorite(id: String): Flow<Boolean> {
        return favoriteMovieDao.isFavorite(id)
    }

    override suspend fun addFavorite(filme: Filme) {
        favoriteMovieDao.insertFavorite(filme)
    }

    override suspend fun removeFavorite(id: String) {
        favoriteMovieDao.deleteFavoriteById(id)
    }
}
