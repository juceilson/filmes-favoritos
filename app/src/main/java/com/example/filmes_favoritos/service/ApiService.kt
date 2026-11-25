package com.example.filmes_favoritos.service

import com.example.filmes_favoritos.database.Filme
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun searchMovies(
        @Query("s") query: String,
        @Query("apikey") apiKey: String = "2f43ff0c"
    ): Search

    @GET("/")
    suspend fun getMovieDetails(
        @Query("i") id: String,
        @Query("apikey") apiKey: String = "2f43ff0c"
    ): Filme
}