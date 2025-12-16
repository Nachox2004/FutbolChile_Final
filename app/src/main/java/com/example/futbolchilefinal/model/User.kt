package com.example.futbolchilefinal.model

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int,
    @SerializedName("created_at")
    val createdAt: Long,
    val name: String,
    val email: String,
    val password: String,
    val telefono: String,
    @SerializedName("equipo_favorito")
    val equipoFavorito: Int
)
