package com.example.filmes_favoritos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
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
import com.example.filmes_favoritos.ui.viewModels.DetalhesFilmeViewModel

@Composable
fun DetalhesFilmeScreen(
    viewModel: DetalhesFilmeViewModel,
    onNavigateBack: () -> Unit
){
    val filme by viewModel.filme.collectAsState()
    val isFavorite by viewModel.isFavorite.collectAsState()
    Column() {
        Spacer(modifier = Modifier.padding(20.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Button(onClick = onNavigateBack) { Text("Voltar") }
            Text(" Detalhes do Filme", fontSize = 24.sp
            )
        }

        Spacer(modifier = Modifier.padding(16.dp))
        Column(
            modifier = Modifier.fillMaxSize().padding(all = 28.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = filme?.poster,
                contentDescription = filme?.title,
                modifier = Modifier.height(280.dp).fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "${filme?.title}")

            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Ano: ${filme?.year}  Classificação: ${filme?.rating ?: ""}")

            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Gênero: ${filme?.genre ?: ""}")

            Spacer(modifier = Modifier.height(16.dp))
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Sinopse")
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = filme?.plot ?: "Sem sinopse disponível.")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            if (isFavorite) {
                OutlinedButton(onClick = { viewModel.toggleFavorite(filme!!) },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                ) {
                    Text("Remover dos Favoritos", fontSize = 16.sp)
                }
            } else {
                Button(onClick = { viewModel.toggleFavorite(filme!!) },
                    modifier = Modifier.fillMaxWidth().height(50.dp)
                ) {
                    Text("Adicionar aos Favoritos", fontSize = 16.sp)
                }
            }
        }
    }
}