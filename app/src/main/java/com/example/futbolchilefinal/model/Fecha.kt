package com.example.futbolchilefinal.model

import com.google.gson.annotations.SerializedName

data class Fecha(
    val id: Int,
    @SerializedName("created_at")
    val createdAt: Long,
    @SerializedName("equipo_local")
    val equipoLocal: Int,
    @SerializedName("equipo_visitante")
    val equipoVisitante: Int,
    @SerializedName("dia_a_jugar")
    val diaAJugar: String, // Representing Date as String
    @SerializedName("hora_de_partido")
    val horaDePartido: String,
    val jornada: String
)
