package com.example.futbolchilefinal.model

import com.google.gson.annotations.SerializedName

data class Equipo(
    val id: Int,
    @SerializedName("created_at")
    val createdAt: Long,
    val nombre: String,
    val division: String,
    @SerializedName("ano_fundacion")
    val anoFundacion: Int,
    val jugadores: String,
    val estadio: String,
    val escudo: EscudoImage? // Updated to use the EscudoImage class
)
