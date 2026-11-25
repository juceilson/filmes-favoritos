package com.example.filmes_favoritos.database


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "filmes_favoritos")
data class Filme(
    @PrimaryKey
    @SerializedName("imdbID")
    val id: String,

    @SerializedName("Title")
    val title: String,

    @SerializedName("Year")
    val year: String,

    @SerializedName("Genre")
    val genre: String? = null,

    @SerializedName("Plot")
    val plot: String? = null,

    @SerializedName("imdbRating")
    val rating: String? = null,

    @SerializedName("Poster")
    val poster: String? = null
)