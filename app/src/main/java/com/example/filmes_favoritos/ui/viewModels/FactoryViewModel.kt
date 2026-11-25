package com.example.filmes_favoritos.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.filmes_favoritos.repository.IFilmeRepository

class FactoryViewModel(
    private val repository: IFilmeRepository,
    private val id: String? = null
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FilmeListViewModel::class.java) -> {
                FilmeListViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetalhesFilmeViewModel::class.java) -> {
                val movieDetailsId = requireNotNull(id) { "id é requerido para DetalhesFilmeViewModel" }
                DetalhesFilmeViewModel(repository, movieDetailsId) as T
            }
            modelClass.isAssignableFrom(FavoritosViewModel::class.java) -> {
                FavoritosViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Classe ViewModel desconhecida: ${modelClass.name}")
        }
    }
}
