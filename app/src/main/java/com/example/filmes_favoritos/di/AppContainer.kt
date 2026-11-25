package com.example.filmes_favoritos.di

import android.content.Context
import androidx.room.Room
import com.example.filmes_favoritos.database.AppDatabase
import com.example.filmes_favoritos.database.FilmeDao
import com.example.filmes_favoritos.repository.FilmeRepository
import com.example.filmes_favoritos.repository.IFilmeRepository
import com.example.filmes_favoritos.service.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer(private val context: Context): IAppContainer {
    override val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
    private val database: AppDatabase by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "filmes_favoritos_db"
        )
            .fallbackToDestructiveMigration(dropAllTables = true)
            .build()
    }

    override val filmeDao: FilmeDao by lazy {
        database.filmeDao()
    }

    override val filmeRepository: IFilmeRepository by lazy {
        FilmeRepository(
            apiService = apiService,
            favoriteMovieDao = filmeDao
        )
    }
}