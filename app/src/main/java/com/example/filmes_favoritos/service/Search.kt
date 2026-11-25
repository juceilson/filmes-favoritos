package com.example.filmes_favoritos.service

import com.example.filmes_favoritos.database.Filme
import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("Search")
    val search: List<Filme>?,
    @SerializedName("totalResults")
    val totalResults: String?,
    @SerializedName("Response")
    val response: String?,
    @SerializedName("Error")
    val error: String?
)