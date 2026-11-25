package com.example.filmes_favoritos.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmes_favoritos.database.Filme
import com.example.filmes_favoritos.repository.IFilmeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetalhesFilmeViewModel(
    private val repository: IFilmeRepository,
    private val id: String
): ViewModel() {
    private val _filme = MutableStateFlow<Filme?>(null)
    val filme: StateFlow<Filme?> = _filme.asStateFlow()

    val isFavorite: StateFlow<Boolean> = repository.isFavorite(id)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    init { fetchMovie() }

    private fun fetchMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            _filme.value = repository.getMovieDetails(id)
        }
    }

    fun toggleFavorite(filmeDetalhes: Filme){
        viewModelScope.launch(Dispatchers.IO) {
            if (isFavorite.value){
                repository.removeFavorite(id)
            } else {
                val filmeFavorito = Filme(
                    id = filmeDetalhes.id,
                    title = filmeDetalhes.title,
                    year = filmeDetalhes.year,
                    genre = filmeDetalhes.genre ?: "",
                    plot = filmeDetalhes.plot ?: "Sem sinopse disponível.",
                    rating = filmeDetalhes.rating ?: "",
                    poster = filmeDetalhes.poster ?: ""
                )
                repository.addFavorite(filmeFavorito)
            }
        }
    }
}