package com.example.filmes_favoritos.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmes_favoritos.database.Filme
import com.example.filmes_favoritos.repository.IFilmeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FilmeListViewModel(
    private val repository: IFilmeRepository
) : ViewModel() {
    private val _nomeFilme = MutableStateFlow<String>("")
    val nomeFilme: StateFlow<String> = _nomeFilme.asStateFlow()

    private val _filmes = MutableStateFlow<List<Filme>>(emptyList())
    val filmes: StateFlow<List<Filme>> = _filmes.asStateFlow()

    init { buscarFilmes(_nomeFilme.value)}

    fun onSearchChanged(novoNomeFilme: String) {
        _nomeFilme.value = novoNomeFilme
    }

    fun executeSearch(){
        buscarFilmes(_nomeFilme.value)
    }

    fun buscarFilmes(nomeFilme: String){
        if (nomeFilme.isBlank()) return

        viewModelScope.launch(Dispatchers.IO) {
            _filmes.value = repository.searchMovies((nomeFilme.trim()))
        }
    }
}