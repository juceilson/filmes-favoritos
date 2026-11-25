package com.example.filmes_favoritos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.filmes_favoritos.database.Filme
import com.example.filmes_favoritos.ui.viewModels.FavoritosViewModel

@Composable
fun FavoritosScreen(
    viewModel: FavoritosViewModel,
    onMovieClick: (String) -> Unit,
    onNavigateBack: () -> Unit
){
    val favoritos by viewModel.favoritesState.collectAsState()

    Column() {
        Spacer(modifier = Modifier.padding(20.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Button(onClick = onNavigateBack) { Text("Voltar") }
            Text("Filmes Favoritos", fontSize = 24.sp)
        }

        Spacer(modifier = Modifier.padding(16.dp))
        Column(
            modifier = Modifier.fillMaxSize().padding(all = 28.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items( items = favoritos, key = { it.id }) {
                    filme -> FilmeFavoritoItemCard(
                    filme = filme,
                        onClick = { onMovieClick(filme.id) },
                        onDeleteClick = { viewModel.removeFavorite(filme.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun FilmeFavoritoItemCard(
    filme: Filme,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth().clickable { onClick() }) {
        Row(modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            AsyncImage(
                model = filme.poster,
                contentDescription = filme.title,
                modifier = Modifier.size(width = 60.dp, height = 90.dp)
                    .clip(RoundedCornerShape(6.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)
            ) {
                Text(text = filme.title)

                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Ano: ${filme.year}")

                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "Gênero: ${filme.genre ?: "N/A"}")
            }

            IconButton(onClick = onDeleteClick) {
                Text("Excluir")
            }
        }
    }
}
