package com.example.futbolchilefinal.model

import com.google.gson.annotations.SerializedName

// Data class for the sign-up request body
data class SignUpRequest(
    val name: String,
    val email: String,
    val password: String,
    val telefono: String,
    @SerializedName("equipo_favorito")
    val equipoFavorito: Int
)
