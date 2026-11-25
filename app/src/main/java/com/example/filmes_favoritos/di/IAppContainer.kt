package com.example.filmes_favoritos.di

import com.example.filmes_favoritos.database.FilmeDao
import com.example.filmes_favoritos.repository.IFilmeRepository
import com.example.filmes_favoritos.service.ApiService

interface IAppContainer {
    val filmeDao: FilmeDao
    val apiService: ApiService
    val filmeRepository: IFilmeRepository
}